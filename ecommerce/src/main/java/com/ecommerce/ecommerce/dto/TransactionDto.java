package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.PaymentProvider;
import com.ecommerce.ecommerce.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        Long userId,              // only user ID
        Long orderId,             // only order ID
        PaymentProvider paymentProvider,
        String paymentReference,
        BigDecimal amount,
        TransactionStatus status,
        LocalDateTime createdAt
) {
}
