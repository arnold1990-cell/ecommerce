package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.NotificationType;

import java.time.LocalDateTime;

public record NotificationDto(
        Long id,
        Long userId,              // store only the user ID
        String title,
        String message,
        NotificationType type,
        boolean isRead,
        LocalDateTime createdAt
) {
}
