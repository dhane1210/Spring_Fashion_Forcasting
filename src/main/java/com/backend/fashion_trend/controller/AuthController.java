package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.config.JwtConfig;
import com.backend.fashion_trend.dtos.JwtResponse;
import com.backend.fashion_trend.dtos.UserLoginRequest;
import com.backend.fashion_trend.services.AuthService;
import com.backend.fashion_trend.services.JwtService;
import com.backend.fashion_trend.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<@NotNull JwtResponse> userLogin(
            @RequestBody @Valid UserLoginRequest request,
            HttpServletResponse response
    ) {
        var user = authService.login(request);

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        var cookie = new Cookie("refreshToken", refreshToken.toString());
        cookie.setMaxAge(Math.toIntExact(jwtConfig.getRefreshTokenExpiration()));
        cookie.setPath("/auth/refresh");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<@NotNull JwtResponse> refresh(
            @CookieValue("refreshToken")
            String refreshToken
    ) {
        var jwt = jwtService.parseToken(refreshToken);

        if (jwt == null || jwt.isExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userService.findUser(jwt.getUserId());
        return ResponseEntity.ok(new JwtResponse(jwtService.generateAccessToken(user).toString()));
    }
}
