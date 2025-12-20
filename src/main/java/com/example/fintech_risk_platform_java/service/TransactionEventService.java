package com.example.fintech_risk_platform_java.service;

import org.springframework.stereotype.Service;

import com.example.fintech_risk_platform_java.model.TransactionEventEntity;
import com.example.fintech_risk_platform_java.repository.TransactionEventRepository;
import com.example.fintech_risk_platform_java.utils.kafka.event.TransactionEvent;

@Service
public class TransactionEventService {
    
    private final TransactionEventRepository repository;

    public TransactionEventService(TransactionEventRepository repository) {
        this.repository = repository;
    }

    public void persist(TransactionEvent event, String performedBy) {
        TransactionEventEntity entity = TransactionEventEntity.builder()
            .transactionId(event.getTransactionId())
            .type(event.getType())
            .amount(event.getAmount())
            .status(event.getstStatus())
            .performedBy(performedBy)
            .timesteamp(event.getTimestamp())
            .build();

        repository.save(entity);
    }
}
