package com.backend.fashion_trend.services;


import com.backend.fashion_trend.config.JwtConfig;
import com.backend.fashion_trend.entities.Jwt;
import com.backend.fashion_trend.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtConfig config;

    public Jwt generateAccessToken(User user) {
        return generateToken(user, config.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, config.getRefreshTokenExpiration());
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(config.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Jwt parseToken(String token) {
        try {
            return new Jwt(config.getSecretKey(), getClaims(token));
        } catch (JwtException ex) {
            return null;
        }
    }

    private Jwt generateToken(User user, Long tokenExpiration) {
        var claims = Jwts.claims()
                .subject(user.getId())
                .add("email", user.getEmail())
                .add("username",  user.getUsername())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();

        return new Jwt(config.getSecretKey(), claims);
    }
}