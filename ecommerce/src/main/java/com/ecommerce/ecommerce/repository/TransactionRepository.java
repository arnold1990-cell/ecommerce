package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
