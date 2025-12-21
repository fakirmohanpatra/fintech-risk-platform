package com.example.fintech_risk_platform_java.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fintech_risk_platform_java.model.TransactionEventEntity;
import com.example.fintech_risk_platform_java.service.TransactionEventService;

@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final TransactionEventService service;

    public RiskController(TransactionEventService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('RISK_ANALYST', 'ADMIN')")
    @GetMapping("/events")
    public List<TransactionEventEntity> getRiskEvents() {

        return service.getAllEvents();
    }
}
