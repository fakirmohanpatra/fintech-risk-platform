package com.example.fintech_risk_platform_java.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "FinTech Risk Management Platform API",
        version = "1.0",
        description = "API documentation for the FinTech Risk Management Platform"
    ),
    security = @SecurityRequirement(name = "bearerAuth")
)

@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)

public class SwaggerConfig {
    
}
