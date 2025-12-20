package com.example.fintech_risk_platform_java.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.fintech_risk_platform_java.model.Account;
import com.example.fintech_risk_platform_java.model.Transaction;
import com.example.fintech_risk_platform_java.model.TransactionLog;
import com.example.fintech_risk_platform_java.repository.AccountRepository;
import com.example.fintech_risk_platform_java.repository.TransactionLogRepository;
import com.example.fintech_risk_platform_java.repository.TransactionRepository;
import com.example.fintech_risk_platform_java.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionLogRepository transactionLogRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              UserRepository userRepository,
                              TransactionLogRepository transactionLogRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Transactional
    public Transaction credit(String accountNumber, BigDecimal amount, String performedBy) {
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));

        Transaction transaction = new Transaction(
            null,
            "CREDIT",
            amount,
            "SUCCESS",
            null,
            account
        );

        transactionRepository.save(transaction);
        accountRepository.save(account);
        logTransaction(transaction, "CREDIT", performedBy);
        
        return transaction;
    }


    @Transactional
    public Transaction debit(String accountNumber, BigDecimal amount, String performedBy) {
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction transaction = new Transaction(
            null,
            "DEBIT",
            amount,
            "SUCCESS",
            null,
            account
        );

        transactionRepository.save(transaction);
        accountRepository.save(account);
        logTransaction(transaction, "DEBIT", performedBy);
        
        return transaction;
    }

    private void logTransaction(Transaction transaction, String action, String performedBy) {
        TransactionLog log = new TransactionLog();
        log.setTransactionId(transaction.getId());
        log.setAction(action);
        log.setPerformedBy(performedBy);
        transactionLogRepository.save(log);
    }
}
