package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    // Get all items in a cart
    List<CartItem> findByCartId(Long cartId);

    // Optional: find an item in cart by product
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
