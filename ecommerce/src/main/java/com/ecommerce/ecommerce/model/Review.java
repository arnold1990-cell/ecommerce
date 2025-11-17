package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
/**
 * Review Entity - Customer reviews and ratings for products
 * Allows customers to rate and comment on products they've purchased
 */
@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which product is being reviewed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Who wrote this review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Rating from 1 to 5 stars
    // @Min(1) and @Max(5) enforce this range
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating;


    // Optional written review
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
