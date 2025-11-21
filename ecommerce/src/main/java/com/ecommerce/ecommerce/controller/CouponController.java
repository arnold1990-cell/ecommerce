package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Coupon;
import com.ecommerce.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Coupon Controller - Exposes REST endpoints for managing coupons.
 * Uses entity directly (no DTOs).
 */
@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /**
     * Create a new coupon
     */
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }

    /**
     * Update an existing coupon
     */
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(
            @PathVariable Long id,
            @RequestBody Coupon updatedCoupon
    ) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updatedCoupon));
    }

    /**
     * Get coupon by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

    /**
     * Get coupon by code
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }

    /**
     * Get all coupons
     */
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    /**
     * Deactivate (soft delete)
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCoupon(@PathVariable Long id) {
        couponService.deactivateCoupon(id);
        return ResponseEntity.ok("Coupon deactivated successfully");
    }

    /**
     * Hard delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    /**
     * Apply coupon to an order amount
     */
    @GetMapping("/apply")
    public ResponseEntity<BigDecimal> applyCoupon(
            @RequestParam String code,
            @RequestParam BigDecimal amount
    ) {
        return ResponseEntity.ok(couponService.applyCoupon(code, amount));
    }
}

