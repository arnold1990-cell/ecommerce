package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AnalyticsDto;
import com.ecommerce.ecommerce.model.Analytics;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:02+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class AnalyticsMapperImpl implements AnalyticsMapper {

    @Override
    public AnalyticsDto toDto(Analytics analytics) {
        if ( analytics == null ) {
            return null;
        }

        Long id = null;
        Long totalUsers = null;
        Long totalOrders = null;
        BigDecimal totalRevenue = null;
        String topSellingProducts = null;
        LocalDateTime createdAt = null;

        id = analytics.getId();
        totalUsers = analytics.getTotalUsers();
        totalOrders = analytics.getTotalOrders();
        totalRevenue = analytics.getTotalRevenue();
        topSellingProducts = analytics.getTopSellingProducts();
        createdAt = analytics.getCreatedAt();

        AnalyticsDto analyticsDto = new AnalyticsDto( id, totalUsers, totalOrders, totalRevenue, topSellingProducts, createdAt );

        return analyticsDto;
    }

    @Override
    public Analytics toEntity(AnalyticsDto analyticsDto) {
        if ( analyticsDto == null ) {
            return null;
        }

        Analytics.AnalyticsBuilder analytics = Analytics.builder();

        analytics.id( analyticsDto.id() );
        analytics.totalUsers( analyticsDto.totalUsers() );
        analytics.totalOrders( analyticsDto.totalOrders() );
        analytics.totalRevenue( analyticsDto.totalRevenue() );
        analytics.topSellingProducts( analyticsDto.topSellingProducts() );
        analytics.createdAt( analyticsDto.createdAt() );

        return analytics.build();
    }
}
