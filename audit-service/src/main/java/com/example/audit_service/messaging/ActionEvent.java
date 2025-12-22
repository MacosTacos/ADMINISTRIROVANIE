package com.example.audit_service.messaging;


public record ActionEvent(
        String actionId,
        String actionType,
        String entityId,
        String details
) {}
