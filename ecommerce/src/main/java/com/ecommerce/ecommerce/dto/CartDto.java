package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CartDto(
        Long id,
        Long userId,                     // store only the user ID
        LocalDateTime createdAt,
        List<CartItemDto> cartItems
) {
}
