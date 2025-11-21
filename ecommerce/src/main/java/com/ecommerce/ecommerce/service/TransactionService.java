package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.enums.TransactionStatus;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.Transaction;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.TransactionRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // Create a new transaction
    public Transaction createTransaction(Long userId, Long orderId,
                                         String paymentReference,
                                         BigDecimal amount,
                                         com.ecommerce.ecommerce.enums.PaymentProvider provider) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Ensure payment reference is unique
        if (transactionRepository.findByPaymentReference(paymentReference).isPresent()) {
            throw new RuntimeException("Transaction reference already exists");
        }

        Transaction transaction = Transaction.builder()
                .user(user)
                .order(order)
                .paymentReference(paymentReference)
                .amount(amount)
                .paymentProvider(provider)
                .status(TransactionStatus.PENDING) // default to pending
                .build();

        return transactionRepository.save(transaction);
    }

    // Get all transactions for a user
    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Get all transactions for an order
    public List<Transaction> getTransactionsByOrder(Long orderId) {
        return transactionRepository.findByOrderId(orderId);
    }

    // Update transaction status
    public Transaction updateStatus(Long transactionId, TransactionStatus status) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setStatus(status);
        return transactionRepository.save(transaction);
    }

    // Delete transaction
    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found");
        }
        transactionRepository.deleteById(transactionId);
    }
}

