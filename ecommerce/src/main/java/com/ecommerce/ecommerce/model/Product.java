package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Product Entity - Represents items for sale

 * Contains all information needed to display and sell a product
 */
@Entity
@Table(name = "products")
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the seller (user with SELLER role) who owns this product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    // Link to the category this product belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    // BigDecimal is used for money to avoid rounding errors with double/float
    private BigDecimal discountPrice;

    // Number of items available in inventory
    @NotNull
    @Column(nullable = false)
    private Integer stock;

    // SKU (Stock Keeping Unit) - unique product identifier for inventory
    @NotBlank
    @Column(nullable = false, unique = true)
    private String sku;

    private String imageUrl;

    // Calculated from reviews (average of all ratings)
    private Double averageRating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Flag to show/hide product without deleting it
    @Column(nullable = false)
    private boolean isActive = true;
}
/*
 * Author: Arnold Madamombe
 * Date: 12-Nov-2025
 * Description: Full set of e-commerce entities
 */
