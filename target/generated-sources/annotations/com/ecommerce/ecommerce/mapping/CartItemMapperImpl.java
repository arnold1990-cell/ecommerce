package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CartItemDto;
import com.ecommerce.ecommerce.model.CartItem;
import com.ecommerce.ecommerce.model.Product;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemDto toDto(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        Long productId = null;
        Long id = null;
        int quantity = 0;

        productId = cartItemProductId( cartItem );
        id = cartItem.getId();
        if ( cartItem.getQuantity() != null ) {
            quantity = cartItem.getQuantity();
        }

        String productName = null;
        BigDecimal productPrice = null;

        CartItemDto cartItemDto = new CartItemDto( id, productId, productName, productPrice, quantity );

        return cartItemDto;
    }

    @Override
    public CartItem toEntity(CartItemDto cartItemDto) {
        if ( cartItemDto == null ) {
            return null;
        }

        CartItem.CartItemBuilder cartItem = CartItem.builder();

        cartItem.product( cartItemDtoToProduct( cartItemDto ) );
        cartItem.id( cartItemDto.id() );
        cartItem.quantity( cartItemDto.quantity() );

        return cartItem.build();
    }

    private Long cartItemProductId(CartItem cartItem) {
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    protected Product cartItemDtoToProduct(CartItemDto cartItemDto) {
        if ( cartItemDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( cartItemDto.productId() );

        return product.build();
    }
}
