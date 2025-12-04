package com.backend.fashion_trend.exceptions;

public class JwtTokenNotFoundException extends RuntimeException {
    public JwtTokenNotFoundException() {
        super("Jwt token not found");
    }
}
