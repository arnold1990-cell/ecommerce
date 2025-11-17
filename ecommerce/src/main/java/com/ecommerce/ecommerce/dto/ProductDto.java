package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        Long sellerId,          // only seller's user ID
        Long categoryId,        // only category ID
        String name,
        String description,
        BigDecimal price,
        BigDecimal discountPrice,
        Integer stock,
        String sku,
        String imageUrl,
        Double averageRating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean isActive
) {
}
