package com.example.fintech_risk_platform_java.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.fintech_risk_platform_java.model.enums.TransactionStatus;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Document(collection = "transaction_events")
public class TransactionEventEntity {
    
    @Id
    private String id;

    private Long transactionId;
    private String type;
    private BigDecimal amount;
    private String performedBy;
    private TransactionStatus status;
    private Instant timestamp;
}
