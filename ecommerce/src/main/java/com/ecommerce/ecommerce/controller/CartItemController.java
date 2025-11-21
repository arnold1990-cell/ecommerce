package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.CartItem;
import com.ecommerce.ecommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * Request body:
     * {
     *   "cartId": 1,
     *   "productId": 2,
     *   "quantity": 3
     * }
     */
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
     * PUT: /api/cart-items/{cartItemId}?quantity=5
     */
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
     * DELETE: /api/cart-items/{cartItemId}
     */
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET all items in a cart
     * GET: /api/cart-items/cart/{cartId}
     */
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        List<CartItem> items = cartItemService.getCartItems(cartId);
        return ResponseEntity.ok(items);
    }

    /**
     * GET a single cart item by ID
     * GET: /api/cart-items/{cartItemId}
     */
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        return ResponseEntity.ok(cartItem);
    }

    /**
     * CLEAR all items in a cart
     * DELETE: /api/cart-items/cart/{cartId}/clear
     */
    @DeleteMapping("/cart/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartItemService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
