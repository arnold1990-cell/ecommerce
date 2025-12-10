package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    // Create new order item
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<OrderItem> create(
            @RequestParam Long orderId,
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(
                orderItemService.createOrderItem(orderId, productId, quantity)
        );
    }

    // Get items for a specific order
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getItemsByOrder(orderId));
    }

    // Update quantity
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItem> updateQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(orderItemService.updateQuantity(itemId, quantity));
    }

    // Delete an order item
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> delete(@PathVariable Long itemId) {
        orderItemService.deleteOrderItem(itemId);
        return ResponseEntity.ok("Order item deleted successfully");
    }
}
