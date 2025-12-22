package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository repository;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository repository,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.repository = repository;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return repository.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest req = repository.findById(requestId).orElseThrow();
        CredentialRecord credential = credentialService.getCredentialByCode(
                credentialService.getAllCredentials()
                        .stream()
                        .filter(c -> c.getId().equals(req.getCredentialId()))
                        .findFirst()
                        .get()
                        .getCredentialCode()
        );

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            req.setStatus("FAILED");
            req.setResultMessage("Credential expired");
        } else {
            req.setStatus("SUCCESS");
            req.setResultMessage("Credential is valid");
        }

        req.setVerifiedAt(LocalDateTime.now());

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(req.getCredentialId());
        audit.setEventType("VERIFICATION_" + req.getStatus());
        audit.setDetails(req.getResultMessage());

        auditService.logEvent(audit);

        return repository.save(req);
    }
}
