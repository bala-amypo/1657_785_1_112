// package com.example.demo.repository;

// import com.example.demo.entity.AuditTrailRecord;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface AuditTrailRecordRepository extends JpaRepository<AuditTrailRecord, Long> {
// }
package com.example.demo.repository;

import com.example.demo.entity.AuditTrailRecord;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AuditTrailRecord;

@Repository
public interface AuditTrailRecordRepository extends JpaRepository<AuditTrailRecord,Long> {
    AuditTrailRecord save(AuditTrailRecord record);
    List<AuditTrailRecord> findByCredentialId(Long credentialId);
}
