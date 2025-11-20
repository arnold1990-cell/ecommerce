package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {

    // Converts Cart entity → CartDto
    // Maps the nested User object's ID (cart.user.id) to a simple userId field on the DTO
    // CartItemMapper is used automatically to convert the list of CartItems within the Cart
    @Mapping(source = "user.id", target = "userId")
    CartDto toDto(Cart cart);

    // Converts CartDto → Cart entity
    // Maps the DTO's userId back into the nested User object (cart.user.id)
    // CartItemMapper is used automatically to convert the list of CartItemDtos back into CartItems
    @Mapping(source = "userId", target = "user.id")
    Cart toEntity(CartDto cartDto);

    // Converts a list of CartItem entities → CartItemDto objects
    // Useful for returning multiple cart items to the frontend
    List<CartItemDto> toDtoList(List<CartItem> cartItems);

    // Converts a list of CartItemDto objects → CartItem entities
    // Useful for saving/updating multiple cart items from frontend input
    List<CartItem> toEntityList(List<CartItemDto> cartItemDto);

}



