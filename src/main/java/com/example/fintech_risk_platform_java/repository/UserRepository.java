package com.example.fintech_risk_platform_java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fintech_risk_platform_java.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
