package com.ecommerce.ecommerce.model;
import com.ecommerce.ecommerce.enums.DiscountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Coupon Entity - Discount codes for orders
 * Allows admin to create promotional discount codes
 */
@Entity
@Table(name = "coupons", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // The coupon code user enters (e.g., "SAVE20")
    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    // Is this a percentage off or fixed amount off?
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType; // PERCENTAGE or FIXED

    // The discount amount (20 for 20% or $20 depending on type)
    @Min(0)
    @Column(nullable = false)
    private BigDecimal discountValue;

    // When coupon becomes active
    @Column(nullable = false)
    private LocalDateTime startDate;

    // When coupon expires
    @Column(nullable = false)
    private LocalDateTime endDate;

    // Minimum order total required to use this coupon
    @Min(0)
    private BigDecimal minOrderAmount;

    // Maximum number of times this coupon can be used (across all users)
    @Min(0)
    private Integer usageLimit; // total number of times this coupon can be used

    // Flag to enable/disable coupon
    @Column(nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

