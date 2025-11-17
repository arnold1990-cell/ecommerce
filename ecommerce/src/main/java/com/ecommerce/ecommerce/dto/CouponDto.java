package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponDto(
        Long id,
        String code,
        DiscountType discountType,
        BigDecimal discountValue,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal minOrderAmount,
        Integer usageLimit,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
