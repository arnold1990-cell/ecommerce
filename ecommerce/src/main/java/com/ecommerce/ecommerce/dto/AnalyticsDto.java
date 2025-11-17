package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AnalyticsDto(
        Long id,
        Long totalUsers,
        Long totalOrders,
        BigDecimal totalRevenue,
        String topSellingProducts,
        LocalDateTime createdAt
) {
}
