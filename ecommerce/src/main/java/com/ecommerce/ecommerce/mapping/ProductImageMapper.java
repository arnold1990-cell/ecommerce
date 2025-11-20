package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ProductImageDto;
import com.ecommerce.ecommerce.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProductImageMapper {

    // Converts ProductImage entity → ProductImageDto
    // Maps the nested Product object's ID (productImage.product.id) to a simple productId field on the DTO
    // Useful for sending product image data to the frontend without exposing the full Product entity
    @Mapping(source = "product.id", target = "productId")
    ProductImageDto toDto(ProductImage productImage);

    // Converts ProductImageDto → ProductImage entity
    // Maps the DTO's productId back into the nested Product object (productImage.product.id)
    // Allows reconstruction of the Product relationship when persisting or updating ProductImages
    @Mapping(source = "productId", target = "product.id")
    ProductImage toEntity(ProductImageDto productImageDto);

}

