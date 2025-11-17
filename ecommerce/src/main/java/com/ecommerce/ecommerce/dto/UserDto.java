package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.RoleType;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String fullName,
        String email,
        String phone,
        RoleType role,
        boolean isVerified,
        boolean isActive,
        String profileImage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
