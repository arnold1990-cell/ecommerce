package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.OrderDto;
import com.ecommerce.ecommerce.dto.OrderItemDto;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    // Converts Order entity → OrderDto
    // Maps nested User and Address IDs to simple userId and addressId fields on the DTO
    // OrderItemMapper is used automatically to convert the list of OrderItems if needed
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address.id", target = "addressId")
    OrderDto toDto(Order order);

    // Converts OrderDto → Order entity
    // Maps DTO's userId and addressId back into the nested User and Address objects
    // Allows reconstruction of relationships when persisting or updating Orders
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "addressId", target = "address.id")
    Order toEntity(OrderDto orderDto);

    // Converts a list of OrderItem entities → OrderItemDto objects
    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);

    // Converts a list of OrderItemDto objects → OrderItem entities
    List<OrderItem> toEntityList(List<OrderItemDto> orderItemDto);

}



