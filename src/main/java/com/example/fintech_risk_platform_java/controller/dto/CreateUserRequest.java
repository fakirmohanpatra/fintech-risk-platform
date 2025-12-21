package com.example.fintech_risk_platform_java.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String password;
    private String role; // ROLE_ADMIN or ROLE_USER
}
