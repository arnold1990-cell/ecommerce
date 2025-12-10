package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Coupon;
import com.ecommerce.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Coupon Controller - Exposes REST endpoints for managing coupons.
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /**
     * Create a new coupon
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.createCoupon(coupon));
    }

    /**
     * Update an existing coupon
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(
            @PathVariable Long id,
            @RequestBody Coupon updatedCoupon
    ) {
        return ResponseEntity.ok(couponService.updateCoupon(id, updatedCoupon));
    }

    /**
     * Get coupon by ID (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

    /**
     * Get coupon by code (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/code/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }

    /**
     * Get all coupons (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    /**
     * Deactivate (soft delete)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCoupon(@PathVariable Long id) {
        couponService.deactivateCoupon(id);
        return ResponseEntity.ok("Coupon deactivated successfully");
    }

    /**
     * Hard delete
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    /**
     * Apply coupon to an order amount
     * Any authenticated user can apply coupons
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/apply")
    public ResponseEntity<BigDecimal> applyCoupon(
            @RequestParam String code,
            @RequestParam BigDecimal amount
    ) {
        return ResponseEntity.ok(couponService.applyCoupon(code, amount));
    }
}
