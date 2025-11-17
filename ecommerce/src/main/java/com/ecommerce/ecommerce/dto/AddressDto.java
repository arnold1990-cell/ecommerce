package com.ecommerce.ecommerce.dto;

import java.time.LocalDateTime;

public record AddressDto(
        Long id,
        Long userId,
        String street,
        String city,
        String country,
        String postalCode,
        String phone,
        boolean isDefault,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
