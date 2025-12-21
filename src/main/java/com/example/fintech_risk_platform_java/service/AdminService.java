package com.example.fintech_risk_platform_java.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fintech_risk_platform_java.controller.dto.CreateUserRequest;
import com.example.fintech_risk_platform_java.model.User;
import com.example.fintech_risk_platform_java.repository.UserRepository;

@Service
public class AdminService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(
            null,
            request.getUsername(),
            passwordEncoder.encode(request.getPassword()),
            request.getRole()
        );
        
        return userRepository.save(user);
    }
}
