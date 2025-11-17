package com.ecommerce.ecommerce.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
/**
 * Wishlist Entity - Products a user wants to buy later
 * Like a "favorites" list for future purchases
 */
@Entity
@Table(name = "wishlists", uniqueConstraints = {
        // Prevents user from adding same product to wishlist twice
        @UniqueConstraint(columnNames = {"user_id", "product_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who owns this wishlist
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Which product they want
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
