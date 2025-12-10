package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    // Add product to wishlist (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(
            @RequestParam Long userId,
            @RequestParam Long productId
    ) {
        return ResponseEntity.ok(wishlistService.addToWishlist(userId, productId));
    }

    // Get all wishlist items for a user (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlist(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistService.getWishlistByUser(userId));
    }

    // Remove product from wishlist (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<String> removeFromWishlist(
            @RequestParam Long userId,
            @RequestParam Long productId
    ) {
        wishlistService.removeFromWishlist(userId, productId);
        return ResponseEntity.ok("Product removed from wishlist");
    }
}
