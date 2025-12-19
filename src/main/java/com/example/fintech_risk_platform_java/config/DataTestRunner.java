package com.example.fintech_risk_platform_java.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fintech_risk_platform_java.model.User;
import com.example.fintech_risk_platform_java.repository.UserRepository;

@Configuration
public class DataTestRunner {

    @Bean
    CommandLineRunner testDb(UserRepository userRepository) {
        return args -> {
            // Test database connection by saving a test user
            User testUser = new User();
            testUser.setUsername("testuser");
            testUser.setPassword("password");
            testUser.setRole("USER");
            userRepository.save(testUser);
            System.out.println("Test user saved to the database.");
        };
    }
    
}
