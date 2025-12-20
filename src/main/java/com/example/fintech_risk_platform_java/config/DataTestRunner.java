package com.example.fintech_risk_platform_java.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.fintech_risk_platform_java.model.User;
import com.example.fintech_risk_platform_java.repository.UserRepository;

@Configuration
public class DataTestRunner {
    
    @Bean
    CommandLineRunner testUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = new User (
                    null, 
                    "admin", 
                    passwordEncoder.encode("admin123"), 
                    "ROLE_ADMIN"
                );

                userRepository.save(user);
                System.out.println("✅ Admin user created: admin / admin123");
            }
        };
    }
}

