package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.OrderItemDto;
import com.ecommerce.ecommerce.model.OrderItem;
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
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDto toDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long productId = null;
        Long id = null;
        int quantity = 0;
        BigDecimal priceAtPurchase = null;

        productId = orderItemProductId( orderItem );
        id = orderItem.getId();
        if ( orderItem.getQuantity() != null ) {
            quantity = orderItem.getQuantity();
        }
        priceAtPurchase = orderItem.getPriceAtPurchase();

        String productName = null;

        OrderItemDto orderItemDto = new OrderItemDto( id, productId, productName, quantity, priceAtPurchase );

        return orderItemDto;
    }

    @Override
    public OrderItem toEntity(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.product( orderItemDtoToProduct( orderItemDto ) );
        orderItem.id( orderItemDto.id() );
        orderItem.quantity( orderItemDto.quantity() );
        orderItem.priceAtPurchase( orderItemDto.priceAtPurchase() );

        return orderItem.build();
    }

    private Long orderItemProductId(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    protected Product orderItemDtoToProduct(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( orderItemDto.productId() );

        return product.build();
    }
}
