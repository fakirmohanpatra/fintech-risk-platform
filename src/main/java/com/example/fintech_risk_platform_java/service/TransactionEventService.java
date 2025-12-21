package com.example.fintech_risk_platform_java.service;

import java.util.List;

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
        if(repository.existsByTransactionId(event.getTransactionId())) {
            // Idempotency check - event already processed
            return;
        }

        TransactionEventEntity entity = TransactionEventEntity.builder()
            .transactionId(event.getTransactionId())
            .type(event.getType())
            .amount(event.getAmount())
            .status(event.getStatus())
            .performedBy(performedBy)
            .timestamp(event.getTimestamp())
            .build();

        repository.save(entity);
    }

    public List<TransactionEventEntity> getAllEvents() {
        return repository.findAll();
    }
}
