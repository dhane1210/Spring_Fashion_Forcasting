package com.backend.fashion_trend.mappers;

import com.backend.fashion_trend.dtos.RegisterUserRequest;
import com.backend.fashion_trend.dtos.UserResponse;
import com.backend.fashion_trend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);

    User toEntity(RegisterUserRequest request);
}
