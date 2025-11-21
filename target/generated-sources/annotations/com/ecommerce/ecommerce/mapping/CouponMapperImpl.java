package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CouponDto;
import com.ecommerce.ecommerce.enums.DiscountType;
import com.ecommerce.ecommerce.model.Coupon;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class CouponMapperImpl implements CouponMapper {

    @Override
    public CouponDto toDto(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        Long id = null;
        String code = null;
        DiscountType discountType = null;
        BigDecimal discountValue = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        BigDecimal minOrderAmount = null;
        Integer usageLimit = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = coupon.getId();
        code = coupon.getCode();
        discountType = coupon.getDiscountType();
        discountValue = coupon.getDiscountValue();
        startDate = coupon.getStartDate();
        endDate = coupon.getEndDate();
        minOrderAmount = coupon.getMinOrderAmount();
        usageLimit = coupon.getUsageLimit();
        createdAt = coupon.getCreatedAt();
        updatedAt = coupon.getUpdatedAt();

        boolean isActive = false;

        CouponDto couponDto = new CouponDto( id, code, discountType, discountValue, startDate, endDate, minOrderAmount, usageLimit, isActive, createdAt, updatedAt );

        return couponDto;
    }

    @Override
    public Coupon toEntity(CouponDto couponDto) {
        if ( couponDto == null ) {
            return null;
        }

        Coupon.CouponBuilder coupon = Coupon.builder();

        coupon.id( couponDto.id() );
        coupon.code( couponDto.code() );
        coupon.discountType( couponDto.discountType() );
        coupon.discountValue( couponDto.discountValue() );
        coupon.startDate( couponDto.startDate() );
        coupon.endDate( couponDto.endDate() );
        coupon.minOrderAmount( couponDto.minOrderAmount() );
        coupon.usageLimit( couponDto.usageLimit() );
        coupon.isActive( couponDto.isActive() );
        coupon.createdAt( couponDto.createdAt() );
        coupon.updatedAt( couponDto.updatedAt() );

        return coupon.build();
    }
}
