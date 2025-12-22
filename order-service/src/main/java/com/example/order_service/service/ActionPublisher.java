package com.example.order_service.service;


import com.example.order_service.config.RabbitConfig;
import com.example.order_service.messaging.ActionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActionPublisher {
    private final RabbitTemplate rabbitTemplate;

    public ActionPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(ActionEvent actionEvent) {
        rabbitTemplate.convertAndSend(RabbitConfig.ORDER_ACTIONS_QUEUE, actionEvent);
    }
}
