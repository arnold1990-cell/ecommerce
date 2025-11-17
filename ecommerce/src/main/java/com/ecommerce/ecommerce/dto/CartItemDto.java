package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;

public record CartItemDto(
        Long id,
        Long productId,       // only store product ID
        String productName,   // optional: for convenience in APIs
        BigDecimal productPrice, // optional: to show price without fetching Product entity
        int quantity
) {
}
