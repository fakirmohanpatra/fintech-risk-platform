package com.example.fintech_risk_platform_java.utils.kafka.event;

import java.math.BigDecimal;
import java.time.Instant;

import com.example.fintech_risk_platform_java.model.enums.TransactionStatus;

public class TransactionEvent {


    private Long transactionId;
    private String type;
    private BigDecimal amount;
    private TransactionStatus status;
    private Instant timestamp;

    public TransactionEvent(Long transactionId,
                            String type,
                            BigDecimal amount,
                            TransactionStatus status,
                            Instant timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    // getters only (events are immutable)
    public Long getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public TransactionStatus getstStatus() { return status; }
    public Instant getTimestamp() { return timestamp; }
}
