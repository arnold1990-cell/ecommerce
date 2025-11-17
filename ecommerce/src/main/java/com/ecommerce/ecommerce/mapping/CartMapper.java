package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring",uses = {CartItem.class})
public interface CartMapper {

    @Mapping(source = "user.id",target = "userId")
        CartDto toDto(Cart cart);
    @Mapping(source = "userId",target = "user.id")
        Cart toEntity(CartDto cartDto);

        List<CartItemDto> toDtoList(List<CartItem> cartItems);

        List<CartItem> toEntityList(List<CartItemDto> cartItemDto);

    }


