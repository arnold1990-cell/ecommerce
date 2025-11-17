package com.ecommerce.ecommerce.enums;
/**
 * PaymentStatus tracks the current state of a payment
 * - PENDING: Payment has been initiated but not completed
 * - PAID: Payment was successful
 * - FAILED: Payment attempt failed
 * - REFUNDED: Payment was returned to the customer
 */
public enum PaymentStatus {
    PENDING,
    PAID,
    FAILED,
    REFUNDED
}
