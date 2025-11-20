package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    /**
     * CREATE OR GET A CART FOR USER
     */
    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    Cart cart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(cart);
                });
    }

    /**
     * GET ALL CARTS
     */
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    /**
     * GET CART BY ID
     */
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    /**
     * DELETE CART
     */
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
