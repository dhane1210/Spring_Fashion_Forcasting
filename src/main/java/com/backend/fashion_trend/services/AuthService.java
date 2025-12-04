package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.UserLoginRequest;
import com.backend.fashion_trend.entities.User;
import com.backend.fashion_trend.repositories.UserRepository;
import com.backend.fashion_trend.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public User login(UserLoginRequest request) {

        if (request == null)
            throw new IllegalArgumentException("User login request cannot be null");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null)
            throw new UserNotFoundException("User not found for user email: " + request.getEmail());

        return user;
    }
}
