package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // Create new order item
    public OrderItem createOrderItem(Long orderId, Long productId, Integer quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity < 1) {
            throw new RuntimeException("Quantity must be at least 1");
        }

        // Freeze price at purchase time
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .priceAtPurchase(product.getPrice())
                .build();

        return orderItemRepository.save(orderItem);
    }

    // Find all order items for a specific order
    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    // Update quantity
    public OrderItem updateQuantity(Long itemId, Integer quantity) {
        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        if (quantity < 1) {
            throw new RuntimeException("Quantity must be at least 1");
        }

        item.setQuantity(quantity);
        return orderItemRepository.save(item);
    }

    // Delete an order item
    public void deleteOrderItem(Long itemId) {
        if (!orderItemRepository.existsById(itemId)) {
            throw new RuntimeException("Order item not found");
        }
        orderItemRepository.deleteById(itemId);
    }
}
