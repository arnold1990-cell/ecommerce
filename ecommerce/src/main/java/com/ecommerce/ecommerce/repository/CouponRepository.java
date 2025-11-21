package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Optional<Object> findByCode(String code);
}
