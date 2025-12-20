package com.example.fintech_risk_platform_java.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.fintech_risk_platform_java.model.TransactionEventEntity;

public interface TransactionEventRepository extends MongoRepository<TransactionEventEntity, String>{
    boolean existsByTransactionId(Long transactionId); // idempotency check
}
