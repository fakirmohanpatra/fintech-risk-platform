package com.example.fintech_risk_platform_java.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.fintech_risk_platform_java.model.Account;
import com.example.fintech_risk_platform_java.model.Transaction;
import com.example.fintech_risk_platform_java.model.TransactionLog;
import com.example.fintech_risk_platform_java.model.enums.TransactionStatus;
import com.example.fintech_risk_platform_java.repository.AccountRepository;
import com.example.fintech_risk_platform_java.repository.TransactionEventRepository;
import com.example.fintech_risk_platform_java.repository.TransactionLogRepository;
import com.example.fintech_risk_platform_java.repository.TransactionRepository;
import com.example.fintech_risk_platform_java.repository.UserRepository;
import com.example.fintech_risk_platform_java.utils.kafka.event.TransactionEvent;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    private final TransactionEventService transactionEventService;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final RiskEngineService riskEngineService;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              UserRepository userRepository,
                              TransactionLogRepository transactionLogRepository,
                              RiskEngineService riskEngineService,
                              TransactionEventService transactionEventService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionLogRepository = transactionLogRepository;
        this.riskEngineService = riskEngineService;
        this.transactionEventService = transactionEventService;
    }

    @Transactional
    public Transaction credit(String accountNumber, BigDecimal amount, String performedBy) {
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // 1️⃣ Create PENDING transaction
        Transaction transaction = new Transaction(
            null,
            "CREDIT",
            amount,
            TransactionStatus.PENDING,
            null,
            account
        );
        transactionRepository.save(transaction);

        // 2️⃣ Run risk checks
        if (riskEngineService.isHighRisk(transaction)) {
            transaction.setStatus(TransactionStatus.BLOCKED_RISK);
            transactionRepository.save(transaction);

            logTransaction(transaction, "BLOCKED_RISK", performedBy);
            throw new RuntimeException("Transaction blocked by risk engine");
        }

        // 3️⃣ Apply balance change
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        // 4️⃣ Mark success
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        logTransaction(transaction, "CREDIT_SUCCESS", performedBy);

        // create and persist event for analytics/Kafka
        TransactionEvent event = new TransactionEvent(
            transaction.getId(),
            transaction.getType(),
            transaction.getAmount(),
            transaction.getStatus(),
            Instant.now()
        );

        transactionEventService.persist(event, performedBy);
        
        return transaction;
    }

    @Transactional
    public Transaction debit(String accountNumber, BigDecimal amount, String performedBy) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // 1️⃣ Create PENDING transaction
        Transaction transaction = new Transaction(
                null,
                "DEBIT",
                amount,
                TransactionStatus.PENDING,
                null,
                account
        );
        transactionRepository.save(transaction);

        // 2️⃣ Risk check
        if (riskEngineService.isHighRisk(transaction)) {
            transaction.setStatus(TransactionStatus.BLOCKED_RISK);
            transactionRepository.save(transaction);

            logTransaction(transaction, "BLOCKED_RISK", performedBy);
            throw new RuntimeException("Transaction blocked by risk engine");
        }

        // 3️⃣ Apply debit
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        // 4️⃣ Finalize
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        logTransaction(transaction, "DEBIT_SUCCESS", performedBy);
        
        // create and persist event for analytics/Kafka
        TransactionEvent event = new TransactionEvent(
            transaction.getId(),
            transaction.getType(),
            transaction.getAmount(),
            transaction.getStatus(),
            Instant.now()
        );

        transactionEventService.persist(event, performedBy);

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
