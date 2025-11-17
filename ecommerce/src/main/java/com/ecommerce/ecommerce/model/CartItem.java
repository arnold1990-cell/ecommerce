package com.ecommerce.ecommerce.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
/**
 * CartItem Entity - Represents one product in a shopping cart
 *
 * Tracks which products are in the cart and their quantities
 */
@Entity
@Table(name = "cart_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which cart this item belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // How many of this product are in the cart
    // @Min(1) ensures quantity is at least 1 (can't have 0 items)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(1)
    @Column(nullable = false)
    private Integer quantity;
}
