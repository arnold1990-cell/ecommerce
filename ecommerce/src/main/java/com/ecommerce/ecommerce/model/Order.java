package com.ecommerce.ecommerce.model;

import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.enums.PaymentMethod;
import com.ecommerce.ecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Order Entity - Represents a completed purchase
 * Created when user checks out their cart
 * Tracks payment, shipping, and order status
 */
@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who placed this order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Total amount charged for this order
    @NotNull
    @Column(nullable = false)
    private BigDecimal totalAmount;

    // Where to ship this order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    // How the customer paid
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // e.g., CREDIT_CARD, PAYPAL, STRIPE

    // Status of the payment
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus; // PENDING, PAID, FAILED, REFUNDED

    // Status of order fulfillment
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus; // PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED, RETURNED

    // Shipping tracking number (added when order ships)
    private String trackingNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // List of products in this order
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}
