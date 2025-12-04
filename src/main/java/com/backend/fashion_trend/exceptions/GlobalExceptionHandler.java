package com.backend.fashion_trend.exceptions;
import com.backend.fashion_trend.dtos.ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleRequestBodyException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleInvalidRequestDataException() {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Invalid request datatype"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleUserNotFoundException(
            UserNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleBadCredentialsException() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Invalid Credentials"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleHttpMessageNotReadableException() {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Malformed JSON or missing fields"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(JwtTokenNotFoundException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleJwtTokenNotFoundException(
            JwtTokenNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleInvalidJwtTokenException(
            InvalidJwtTokenException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
