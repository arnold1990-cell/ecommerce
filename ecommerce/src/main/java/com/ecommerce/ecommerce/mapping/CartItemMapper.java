package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

@Mapping(source = "product.id",target = "productId")
    CartItemDto toDto(CartItem cartItem);
    @Mapping(source = "productId",target = "product.id")
     CartItem toEntity( CartItemDto cartItemDto);



}
