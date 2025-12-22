package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "credentialCode"))
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;
    private String credentialCode;
    private String title;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String credentialType;
    private String status;
    private String metadataJson;

    @ManyToMany
    @JoinTable(
        name = "credential_rule",
        joinColumns = @JoinColumn(name = "credential_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<VerificationRule> rules;

    // getters & setters
}
