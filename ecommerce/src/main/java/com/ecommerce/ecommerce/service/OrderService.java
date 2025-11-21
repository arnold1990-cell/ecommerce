package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.enums.PaymentStatus;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * Create a new order with items
     */
    public Order createOrder(Order order) {

        // Attach order reference to items
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        }

        return orderRepository.save(order);
    }

    /**
     * Get order by ID
     */
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    /**
     * Get all orders of a specific user
     */
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    /**
     * Get all orders (admin)
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Update order status (PROCESSING, SHIPPED, DELIVERED, etc.)
     */
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    /**
     * Update payment status (PAID, FAILED, REFUNDED)
     */
    public Order updatePaymentStatus(Long id, PaymentStatus status) {
        Order order = getOrderById(id);
        order.setPaymentStatus(status);
        return orderRepository.save(order);
    }

    /**
     * Add/Update tracking number
     */
    public Order updateTrackingNumber(Long id, String trackingNumber) {
        Order order = getOrderById(id);
        order.setTrackingNumber(trackingNumber);
        return orderRepository.save(order);
    }

    /**
     * Delete order
     */
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order does not exist");
        }
        orderRepository.deleteById(id);
    }
}
