package com.backend.fashion_trend.exceptions;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
        super("Invalid or expired Jwt token detected.");
    }
}
