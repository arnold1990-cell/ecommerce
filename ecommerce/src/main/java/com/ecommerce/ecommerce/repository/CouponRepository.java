package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
