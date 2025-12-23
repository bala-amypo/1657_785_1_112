package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String credentialCode;
    private String status;
    private LocalDate expiryDate;

    public Long getId() { return id; }
    public String getCredentialCode() { return credentialCode; }
    public String getStatus() { return status; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setStatus(String status) { this.status = status; }
}
