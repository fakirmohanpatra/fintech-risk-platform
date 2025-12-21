package com.example.fintech_risk_platform_java.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fintech_risk_platform_java.controller.dto.CreateUserRequest;
import com.example.fintech_risk_platform_java.model.User;
import com.example.fintech_risk_platform_java.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest request) {
        return adminService.createUser(request);
    }
}
