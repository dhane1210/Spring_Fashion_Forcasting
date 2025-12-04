package com.backend.fashion_trend.dtos;

import com.backend.fashion_trend.enums.Role;
import lombok.Data;

@Data
public class UserResponse {

    private String id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private Role role;
}
