package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ReviewDto;
import com.ecommerce.ecommerce.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // Converts Review entity → ReviewDto
    // Maps the nested Product and User IDs to simple productId and userId fields on the DTO
    // Useful for sending review data to the frontend without exposing full Product or User entities
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    ReviewDto toDto(Review review);

    // Converts ReviewDto → Review entity
    // Maps DTO's productId and userId back into the nested Product and User objects
    // Allows reconstruction of relationships when persisting or updating reviews
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    Review toEntity(ReviewDto reviewDto);

}



