package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.RegisterUserRequest;
import com.backend.fashion_trend.dtos.UserResponse;
import com.backend.fashion_trend.enums.Role;
import com.backend.fashion_trend.entities.User;
import com.backend.fashion_trend.exceptions.UserNotFoundException;
import com.backend.fashion_trend.mappers.UserMapper;
import com.backend.fashion_trend.repositories.UserRepository;
import com.backend.fashion_trend.exceptions.EmailAlreadyRegisteredException;
import com.backend.fashion_trend.exceptions.UsernameAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse registerUser(
            RegisterUserRequest request
    ) {
        if (request == null)
            throw new IllegalArgumentException("Register request cannot be null");

        var user = userMapper.toEntity(request);

        if (userRepository.existsByUsername(request.getUsername()))
            throw new UsernameAlreadyExistsException("Username: " + request.getUsername() + " is already registered in system.");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailAlreadyRegisteredException("Email: " + user.getEmail() + " is already registered in system.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);

        return userMapper.toDto(userRepository.save(user));
    }

    public User findUser(String userId) {

        if (userId == null)
            throw new IllegalArgumentException("User ID cannot be null");

        var user = userRepository.findById(userId).orElse(null);

        if (user == null)
            throw new UserNotFoundException("User not found with user id: " + userId);

        return user;
    }
}
