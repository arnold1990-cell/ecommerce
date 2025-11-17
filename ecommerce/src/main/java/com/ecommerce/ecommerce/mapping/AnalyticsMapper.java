package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AnalyticsDto;
import com.ecommerce.ecommerce.model.Analytics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalyticsMapper {

        AnalyticsDto toDto(Analytics analytics);

      Analytics   toEntity( AnalyticsDto analyticsDto);


}
