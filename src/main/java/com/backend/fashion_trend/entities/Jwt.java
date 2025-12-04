package com.backend.fashion_trend.entities;

import com.backend.fashion_trend.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
@Data
public class Jwt {

    private SecretKey secretKey;

    private Claims claims;

    public boolean isExpired() {
        return claims.getExpiration().before(new Date());
    }

    public String getUserId() {
        return claims.getSubject();
    }

    public Role getRole() {
        return Role.valueOf(claims.get("role", String.class));
    }

    @Override
    public String toString() {
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
