package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

public record WishlistDto(
        Long id,
        Long userId,        // only store user ID
        Long productId,     // only store product ID
        LocalDateTime createdAt
) {
}
