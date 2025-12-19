package com.example.fintech_risk_platform_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fintech_risk_platform_java.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
