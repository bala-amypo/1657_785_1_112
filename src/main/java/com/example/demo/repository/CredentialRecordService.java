package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.*;
import java.time.LocalDate;
import java.util.List;

public interface CredentialRecordRepository extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findExpiredBefore(LocalDate date);

    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialType = :type")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String type);

    List<CredentialRecord> findByHolderId(Long holderId);

    CredentialRecord findByCredentialCode(String code);
}
