package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AnalyticsDto;
import com.ecommerce.ecommerce.model.Analytics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalyticsMapper {

    // Converts Analytics entity → AnalyticsDto
    // Useful for sending data to the frontend without exposing the full entity
    AnalyticsDto toDto(Analytics analytics);

    // Converts AnalyticsDto → Analytics entity
    // Useful for persisting or updating data received from the frontend
    Analytics toEntity(AnalyticsDto analyticsDto);

}

