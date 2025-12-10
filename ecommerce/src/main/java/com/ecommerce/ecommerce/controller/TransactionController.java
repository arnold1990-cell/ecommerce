package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.enums.PaymentProvider;
import com.ecommerce.ecommerce.enums.TransactionStatus;
import com.ecommerce.ecommerce.model.Transaction;
import com.ecommerce.ecommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Create a transaction (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam Long userId,
            @RequestParam Long orderId,
            @RequestParam String paymentReference,
            @RequestParam BigDecimal amount,
            @RequestParam PaymentProvider provider
    ) {
        return ResponseEntity.ok(
                transactionService.createTransaction(userId, orderId, paymentReference, amount, provider)
        );
    }

    // Get transactions by user (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUser(userId));
    }

    // Get transactions by order (Authenticated users)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Transaction>> getByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(transactionService.getTransactionsByOrder(orderId));
    }

    // Update transaction status (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{transactionId}/status")
    public ResponseEntity<Transaction> updateStatus(
            @PathVariable Long transactionId,
            @RequestParam TransactionStatus status
    ) {
        return ResponseEntity.ok(transactionService.updateStatus(transactionId, status));
    }

    // Delete a transaction (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> delete(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
