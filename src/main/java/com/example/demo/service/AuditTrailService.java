package com.example.demo.service;

import com.example.demo.entity.AuditTrail;
import java.util.List;

public interface AuditTrailService {
    AuditTrail saveAudit(AuditTrail auditTrail);
    List<AuditTrail> getAllAudits();
}
