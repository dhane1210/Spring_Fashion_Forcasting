package com.backend.fashion_trend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String password;
}
