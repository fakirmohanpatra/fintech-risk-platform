package com.example.fintech_risk_platform_java.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.fintech_risk_platform_java.model.TransactionLog;

public interface TransactionLogRepository extends MongoRepository<TransactionLog, String> {
    
}
