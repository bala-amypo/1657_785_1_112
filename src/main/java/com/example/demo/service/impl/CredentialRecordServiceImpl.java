package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord approveCredential(Long id) {
        CredentialRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        record.setStatus("APPROVED");
        return repository.save(record);
    }

    @Override
    public CredentialRecord rejectCredential(Long id) {
        CredentialRecord record = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        record.setStatus("REJECTED");
        return repository.save(record);
    }
}
