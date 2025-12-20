package com.example.fintech_risk_platform_java.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.example.fintech_risk_platform_java.model.Transaction;

@Service
public class RiskEngineService {
    
    public boolean isHighRisk(Transaction transaction) {

        // Rule 1 : Large amount
        if (transaction.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            return true;
        }

        // rule 2: Suspicious hour
        int hour = Instant.now().atZone(ZoneId.systemDefault()).getHour();

        return hour < 5;
    }
}
