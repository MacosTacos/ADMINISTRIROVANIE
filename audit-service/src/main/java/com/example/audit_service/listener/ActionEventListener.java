package com.example.audit_service.listener;

import com.example.audit_service.config.RabbitConfig;
import com.example.audit_service.messaging.ActionEvent;
import com.example.audit_service.messaging.AuditEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ActionEventListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitConfig.ORDER_ACTIONS_QUEUE)
    public void handle(ActionEvent actionEvent) throws Exception {
        AuditEvent auditEvent = new AuditEvent(actionEvent, Instant.now());
        String json = objectMapper.writeValueAsString(auditEvent);
        System.out.println(json);
    }
}
