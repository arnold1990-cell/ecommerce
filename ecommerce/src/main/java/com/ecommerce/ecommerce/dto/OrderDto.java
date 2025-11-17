package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.enums.PaymentMethod;
import com.ecommerce.ecommerce.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        Long userId,                 // only store user ID
        BigDecimal totalAmount,
        Long addressId,              // only store address ID
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        OrderStatus orderStatus,
        String trackingNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItemDto> orderItems // nested DTO for items
) {
}
