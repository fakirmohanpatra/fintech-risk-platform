package com.example.fintech_risk_platform_java.model;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import lombok.*;

@Document(collection = "transaction_logs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TransactionLog {
    
    @Id
    private String id;

    private Long transactionId;
    private String action;
    private String performedBy;
    private Instant timestamp = Instant.now();
}
