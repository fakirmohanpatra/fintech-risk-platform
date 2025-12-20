package com.example.fintech_risk_platform_java.model;


import java.math.BigDecimal;
import java.time.Instant;

import com.example.fintech_risk_platform_java.model.enums.TransactionStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // "DEBIT" or "CREDIT"
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
 
    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
