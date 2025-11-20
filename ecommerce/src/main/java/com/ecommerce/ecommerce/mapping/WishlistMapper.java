package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.WishlistDto;
import com.ecommerce.ecommerce.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    // Converts Wishlist entity → WishlistDto
    // Maps the nested User and Product IDs to simple userId and productId fields on the DTO
    // Useful for sending wishlist data to the frontend without exposing full User or Product entities
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    WishlistDto toDto(Wishlist wishlist);

    // Converts WishlistDto → Wishlist entity
    // Maps DTO's userId and productId back into the nested User and Product objects
    // Allows reconstruction of relationships when persisting or updating wishlist entries
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Wishlist toEntity(WishlistDto wishlistDto);

}

