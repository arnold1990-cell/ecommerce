package com.ecommerce.ecommerce.model;


import com.ecommerce.ecommerce.enums.PaymentProvider;
import com.ecommerce.ecommerce.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Transaction Entity - Records of payment transactions
 * Tracks payment details for each order
 * Separate from Order to handle payment-specific data
 */
@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who made the payment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Which order this payment is for
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Which payment service was used (Stripe, PayPal, etc.)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentProvider paymentProvider; // STRIPE, PAYPAL, CREDIT_CARD, etc.

    // Unique transaction ID from payment provider
    // Used to track/refund payments
    @NotNull
    @Column(nullable = false, unique = true)
    private String paymentReference; // unique transaction reference from provider

    // Amount that was charged
    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    // Did payment succeed?
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status; // PENDING, SUCCESS, FAILED, REFUNDED

    @CreationTimestamp
    private LocalDateTime createdAt;
}
