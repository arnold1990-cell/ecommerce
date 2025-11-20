package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    // Converts CartItem entity → CartItemDto
    // Maps the nested Product object's ID (cartItem.product.id) to a simple productId field on the DTO
    // Useful for sending cart data to the frontend without exposing the full Product entity
    @Mapping(source = "product.id", target = "productId")
    CartItemDto toDto(CartItem cartItem);

    // Converts CartItemDto → CartItem entity
    // Maps the DTO's productId back into the nested Product object (cartItem.product.id)
    // Allows reconstruction of the Product relationship when persisting or updating CartItem
    @Mapping(source = "productId", target = "product.id")
    CartItem toEntity(CartItemDto cartItemDto);

}

