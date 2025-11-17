package com.ecommerce.ecommerce.enums;
/**
 * PaymentProvider identifies which payment service processed the transaction
 */
public enum PaymentProvider { STRIPE,
    PAYPAL,
    CREDIT_CARD,
    DEBIT_CARD,
    CASH_ON_DELIVERY
}
