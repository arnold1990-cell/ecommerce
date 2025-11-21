package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.OrderDto;
import com.ecommerce.ecommerce.dto.OrderItemDto;
import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.enums.PaymentMethod;
import com.ecommerce.ecommerce.enums.PaymentStatus;
import com.ecommerce.ecommerce.model.Address;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.model.User;
import java.math.BigDecimal;
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
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        Long userId = null;
        Long addressId = null;
        Long id = null;
        BigDecimal totalAmount = null;
        PaymentMethod paymentMethod = null;
        PaymentStatus paymentStatus = null;
        OrderStatus orderStatus = null;
        String trackingNumber = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        List<OrderItemDto> orderItems = null;

        userId = orderUserId( order );
        addressId = orderAddressId( order );
        id = order.getId();
        totalAmount = order.getTotalAmount();
        paymentMethod = order.getPaymentMethod();
        paymentStatus = order.getPaymentStatus();
        orderStatus = order.getOrderStatus();
        trackingNumber = order.getTrackingNumber();
        createdAt = order.getCreatedAt();
        updatedAt = order.getUpdatedAt();
        orderItems = toDtoList( order.getOrderItems() );

        OrderDto orderDto = new OrderDto( id, userId, totalAmount, addressId, paymentMethod, paymentStatus, orderStatus, trackingNumber, createdAt, updatedAt, orderItems );

        return orderDto;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.user( orderDtoToUser( orderDto ) );
        order.address( orderDtoToAddress( orderDto ) );
        order.id( orderDto.id() );
        order.totalAmount( orderDto.totalAmount() );
        order.paymentMethod( orderDto.paymentMethod() );
        order.paymentStatus( orderDto.paymentStatus() );
        order.orderStatus( orderDto.orderStatus() );
        order.trackingNumber( orderDto.trackingNumber() );
        order.createdAt( orderDto.createdAt() );
        order.updatedAt( orderDto.updatedAt() );
        order.orderItems( toEntityList( orderDto.orderItems() ) );

        return order.build();
    }

    @Override
    public List<OrderItemDto> toDtoList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemDto> list = new ArrayList<OrderItemDto>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( orderItemMapper.toDto( orderItem ) );
        }

        return list;
    }

    @Override
    public List<OrderItem> toEntityList(List<OrderItemDto> orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDto.size() );
        for ( OrderItemDto orderItemDto1 : orderItemDto ) {
            list.add( orderItemMapper.toEntity( orderItemDto1 ) );
        }

        return list;
    }

    private Long orderUserId(Order order) {
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long orderAddressId(Order order) {
        Address address = order.getAddress();
        if ( address == null ) {
            return null;
        }
        return address.getId();
    }

    protected User orderDtoToUser(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( orderDto.userId() );

        return user.build();
    }

    protected Address orderDtoToAddress(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.id( orderDto.addressId() );

        return address.build();
    }
}
