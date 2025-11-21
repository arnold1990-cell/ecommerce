package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.enums.DiscountType;
import com.ecommerce.ecommerce.model.Coupon;
import com.ecommerce.ecommerce.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    /**
     * Create a new coupon
     */
    public Coupon createCoupon(Coupon coupon) {
        validateDates(coupon);
        validateUsageLimit(coupon);

        return couponRepository.save(coupon);
    }

    /**
     * Update an existing coupon using only entity
     */
    public Coupon updateCoupon(Long id, Coupon updated) {
        Coupon coupon = getCouponById(id);

        coupon.setCode(updated.getCode());
        coupon.setDiscountType(updated.getDiscountType());
        coupon.setDiscountValue(updated.getDiscountValue());
        coupon.setStartDate(updated.getStartDate());
        coupon.setEndDate(updated.getEndDate());
        coupon.setMinOrderAmount(updated.getMinOrderAmount());
        coupon.setUsageLimit(updated.getUsageLimit());
        coupon.setActive(updated.isActive());

        validateDates(coupon);
        validateUsageLimit(coupon);

        return couponRepository.save(coupon);
    }

    /**
     * Get coupon by ID
     */
    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
    }

    /**
     * Get coupon by code
     */
    public Coupon getCouponByCode(String code) {
        return (Coupon) couponRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found: " + code));
    }

    /**
     * Get all coupons
     */
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    /**
     * Deactivate coupon (soft delete)
     */
    public void deactivateCoupon(Long id) {
        Coupon coupon = getCouponById(id);
        coupon.setActive(false);
        couponRepository.save(coupon);
    }

    /**
     * Hard delete coupon
     */
    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new RuntimeException("Coupon does not exist");
        }
        couponRepository.deleteById(id);
    }

    /**
     * Apply coupon to an order amount
     */
    public BigDecimal applyCoupon(String code, BigDecimal orderAmount) {
        Coupon coupon = getCouponByCode(code);

        if (!coupon.isActive()) {
            throw new RuntimeException("Coupon is inactive");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())) {
            throw new RuntimeException("Coupon expired or not active yet");
        }

        // Check minimum amount
        if (coupon.getMinOrderAmount() != null &&
                orderAmount.compareTo(coupon.getMinOrderAmount()) < 0) {
            throw new RuntimeException("Order amount does not meet coupon minimum");
        }

        // Apply discount
        if (coupon.getDiscountType() == DiscountType.PERCENTAGE) {
            BigDecimal discount = orderAmount
                    .multiply(coupon.getDiscountValue())
                    .divide(BigDecimal.valueOf(100));

            return orderAmount.subtract(discount);

        } else { // FIXED discount
            return orderAmount.subtract(coupon.getDiscountValue())
                    .max(BigDecimal.ZERO);
        }
    }

    /**
     * Validate coupon start/end date logic
     */
    private void validateDates(Coupon coupon) {
        if (coupon.getStartDate().isAfter(coupon.getEndDate())) {
            throw new RuntimeException("Start date cannot be after end date");
        }
    }

    /**
     * Validate coupon usage limit
     */
    private void validateUsageLimit(Coupon coupon) {
        if (coupon.getUsageLimit() != null && coupon.getUsageLimit() < 0) {
            throw new RuntimeException("Usage limit cannot be negative");
        }
    }
}
