package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ProductDto;
import com.ecommerce.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Converts Product entity → ProductDto
    // Maps nested Seller and Category IDs to simple sellerId and categoryId fields on the DTO
    // Useful for sending product data to the frontend without exposing full Seller or Category entities
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    // Converts ProductDto → Product entity
    // Maps DTO's sellerId and categoryId back into the nested Seller and Category objects
    // Allows reconstruction of relationships when persisting or updating Product data
    @Mapping(source = "sellerId", target = "seller.id")
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDto productDto);

}



