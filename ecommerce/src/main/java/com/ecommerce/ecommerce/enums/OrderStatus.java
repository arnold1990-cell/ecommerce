package com.ecommerce.ecommerce.enums;
/**
 * OrderStatus tracks where an order is in the fulfillment process
 * - PENDING: Order placed but not yet processed
 * - PROCESSING: Order is being prepared
 * - SHIPPED: Order has been sent to customer
 * - DELIVERED: Order reached the customer
 * - CANCELLED: Order was cancelled before shipping
 * - RETURNED: Order was sent back by customer
 */
public enum OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED
}
