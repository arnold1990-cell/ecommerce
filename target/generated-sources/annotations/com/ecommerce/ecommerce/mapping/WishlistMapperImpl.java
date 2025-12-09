package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.WishlistDto;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:02+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class WishlistMapperImpl implements WishlistMapper {

    @Override
    public WishlistDto toDto(Wishlist wishlist) {
        if ( wishlist == null ) {
            return null;
        }

        Long userId = null;
        Long productId = null;
        Long id = null;
        LocalDateTime createdAt = null;

        userId = wishlistUserId( wishlist );
        productId = wishlistProductId( wishlist );
        id = wishlist.getId();
        createdAt = wishlist.getCreatedAt();

        WishlistDto wishlistDto = new WishlistDto( id, userId, productId, createdAt );

        return wishlistDto;
    }

    @Override
    public Wishlist toEntity(WishlistDto wishlistDto) {
        if ( wishlistDto == null ) {
            return null;
        }

        Wishlist.WishlistBuilder wishlist = Wishlist.builder();

        wishlist.user( wishlistDtoToUser( wishlistDto ) );
        wishlist.product( wishlistDtoToProduct( wishlistDto ) );
        wishlist.id( wishlistDto.id() );
        wishlist.createdAt( wishlistDto.createdAt() );

        return wishlist.build();
    }

    private Long wishlistUserId(Wishlist wishlist) {
        User user = wishlist.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long wishlistProductId(Wishlist wishlist) {
        Product product = wishlist.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    protected User wishlistDtoToUser(WishlistDto wishlistDto) {
        if ( wishlistDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( wishlistDto.userId() );

        return user.build();
    }

    protected Product wishlistDtoToProduct(WishlistDto wishlistDto) {
        if ( wishlistDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( wishlistDto.productId() );

        return product.build();
    }
}
