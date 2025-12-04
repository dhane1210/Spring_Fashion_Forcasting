package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.ErrorResponse;
import com.backend.fashion_trend.dtos.RegisterUserRequest;
import com.backend.fashion_trend.dtos.UserResponse;
import com.backend.fashion_trend.exceptions.EmailAlreadyRegisteredException;
import com.backend.fashion_trend.exceptions.UsernameAlreadyExistsException;
import com.backend.fashion_trend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<@NotNull UserResponse> registerUser(
            @RequestBody @Valid RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var userDto = userService.registerUser(request);

        var uri = uriBuilder
                .path("/users/{userId}")
                .buildAndExpand(userDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(exception.getMessage()));

    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleEmailAlreadyRegisteredException(
            EmailAlreadyRegisteredException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(exception.getMessage()));

    }
}
