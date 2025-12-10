package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Review;
import com.ecommerce.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Add a review (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestParam Long productId,
            @RequestParam Long userId,
            @RequestParam Integer rating,
            @RequestParam(required = false) String comment
    ) {
        return ResponseEntity.ok(reviewService.addReview(productId, userId, rating, comment));
    }

    // Get reviews for a product (Public)
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    // Get reviews by a user (Public)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    // Update review (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String comment
    ) {
        return ResponseEntity.ok(reviewService.updateReview(id, rating, comment));
    }

    // Delete review (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
