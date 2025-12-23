package com.example.demo.service;
import com.example.demo.entity.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VerificationRuleService extends JpaRepository<VerificationRequest, Long> {
    
}
