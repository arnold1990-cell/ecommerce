package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByOrderId(Long orderId);

    Optional<Transaction> findByPaymentReference(String paymentReference);
}
