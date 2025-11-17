package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

public record ReviewDto(
        Long id,
        Long productId,   // only product ID
        Long userId,      // only user ID
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {
}
