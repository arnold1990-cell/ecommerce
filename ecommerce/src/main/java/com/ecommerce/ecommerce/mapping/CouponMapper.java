package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CouponDto;
import com.ecommerce.ecommerce.model.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    // Converts Coupon entity → CouponDto
    // Useful for sending coupon data to the frontend without exposing the full entity
    CouponDto toDto(Coupon coupon);

    // Converts CouponDto → Coupon entity
    // Useful for saving or updating coupon data received from the frontend
    Coupon toEntity(CouponDto couponDto);

}

