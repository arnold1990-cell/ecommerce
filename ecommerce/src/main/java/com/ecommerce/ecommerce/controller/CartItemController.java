package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.CartItem;
import com.ecommerce.ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    /**
     * ADD product to cart
     * POST: /api/cart-items
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CartItem> addProductToCart(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        CartItem cartItem = cartItemService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * UPDATE quantity of a cart item
     * PUT: /api/cart-items/{cartItemId}
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity
    ) {
        CartItem updated = cartItemService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(updated);
    }

    /**
     * REMOVE cart item
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET all items in a cart
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        List<CartItem> items = cartItemService.getCartItems(cartId);
        return ResponseEntity.ok(items);
    }

    /**
     * GET a single cart item by ID
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * CLEAR all items in a cart
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/cart/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartItemService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
