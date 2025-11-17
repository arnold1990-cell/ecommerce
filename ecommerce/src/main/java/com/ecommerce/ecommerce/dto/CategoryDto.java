package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryDto(
        Long id,
        String name,
        String description,
        Long parentCategoryId,
        List<CategoryDto> subcategories,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
