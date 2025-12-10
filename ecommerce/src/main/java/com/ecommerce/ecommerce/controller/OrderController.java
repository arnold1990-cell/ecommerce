package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.enums.PaymentStatus;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Create order
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    /**
     * Get order by ID
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    /**
     * Get all orders for a specific user
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    /**
     * Get all orders (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * Update order status (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    /**
     * Update payment status (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/payment")
    public ResponseEntity<Order> updatePayment(
            @PathVariable Long id,
            @RequestParam PaymentStatus status
    ) {
        return ResponseEntity.ok(orderService.updatePaymentStatus(id, status));
    }

    /**
     * Add or update tracking number (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/tracking")
    public ResponseEntity<Order> updateTracking(
            @PathVariable Long id,
            @RequestParam String number
    ) {
        return ResponseEntity.ok(orderService.updateTrackingNumber(id, number));
    }

    /**
     * Delete an order (ADMIN ONLY)
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted");
    }
}
