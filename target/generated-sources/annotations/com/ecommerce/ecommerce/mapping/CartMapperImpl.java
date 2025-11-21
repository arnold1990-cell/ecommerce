package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.CartItem;
import com.ecommerce.ecommerce.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartDto toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        Long userId = null;
        Long id = null;
        LocalDateTime createdAt = null;
        List<CartItemDto> cartItems = null;

        userId = cartUserId( cart );
        id = cart.getId();
        createdAt = cart.getCreatedAt();
        cartItems = toDtoList( cart.getCartItems() );

        CartDto cartDto = new CartDto( id, userId, createdAt, cartItems );

        return cartDto;
    }

    @Override
    public Cart toEntity(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        cart.user( cartDtoToUser( cartDto ) );
        cart.id( cartDto.id() );
        cart.createdAt( cartDto.createdAt() );
        cart.cartItems( toEntityList( cartDto.cartItems() ) );

        return cart.build();
    }

    @Override
    public List<CartItemDto> toDtoList(List<CartItem> cartItems) {
        if ( cartItems == null ) {
            return null;
        }

        List<CartItemDto> list = new ArrayList<CartItemDto>( cartItems.size() );
        for ( CartItem cartItem : cartItems ) {
            list.add( cartItemMapper.toDto( cartItem ) );
        }

        return list;
    }

    @Override
    public List<CartItem> toEntityList(List<CartItemDto> cartItemDto) {
        if ( cartItemDto == null ) {
            return null;
        }

        List<CartItem> list = new ArrayList<CartItem>( cartItemDto.size() );
        for ( CartItemDto cartItemDto1 : cartItemDto ) {
            list.add( cartItemMapper.toEntity( cartItemDto1 ) );
        }

        return list;
    }

    private Long cartUserId(Cart cart) {
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected User cartDtoToUser(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( cartDto.userId() );

        return user.build();
    }
}
