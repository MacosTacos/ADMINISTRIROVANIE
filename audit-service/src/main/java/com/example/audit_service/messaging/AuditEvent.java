package com.example.audit_service.messaging;


import java.time.Instant;

public record AuditEvent(
        ActionEvent action,
        Instant auditedAt
) {}
