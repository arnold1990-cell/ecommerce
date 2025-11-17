package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

public record ProductImageDto(
        Long id,
        Long productId,       // store only the product ID
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
