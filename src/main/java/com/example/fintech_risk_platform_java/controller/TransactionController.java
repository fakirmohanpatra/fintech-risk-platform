package com.example.fintech_risk_platform_java.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fintech_risk_platform_java.model.Transaction;
import com.example.fintech_risk_platform_java.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/debit")
    public Transaction debit(@RequestBody Map<String, String> request,
        Authentication auth
    ) {
        return transactionService.debit(
            request.get("accountNumber"), 
            new BigDecimal(request.get("amount")), 
            auth.getName());
    }

    @PostMapping("/credit")
    public Transaction credit(@RequestBody Map<String, String> request,
        Authentication auth
    ) {
        return transactionService.credit(
            request.get("accountNumber"), 
            new BigDecimal(request.get("amount")), 
            auth.getName());
    }
}
