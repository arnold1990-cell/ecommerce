package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.OrderItemDto;
import com.ecommerce.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    // Converts OrderItem entity → OrderItemDto
    // Maps the nested Product object's ID (orderItem.product.id) to a simple productId field on the DTO
    // Useful for sending order item data to the frontend without exposing the full Product entity
    @Mapping(source = "product.id", target = "productId")
    OrderItemDto toDto(OrderItem orderItem);

    // Converts OrderItemDto → OrderItem entity
    // Maps the DTO's productId back into the nested Product object (orderItem.product.id)
    // Allows reconstruction of the Product relationship when persisting or updating order items
    @Mapping(source = "productId", target = "product.id")
    OrderItem toEntity(OrderItemDto orderItemDto);

}

