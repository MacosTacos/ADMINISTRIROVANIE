package com.example.caffe_main.services;

import com.example.caffe_main.config.RabbitConfig;
import com.example.caffe_main.messaging.CafeEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CafePublisher {
    private final RabbitTemplate rabbitTemplate;

    public CafePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(CafeEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.CAFFE_EVENTS_QUEUE, event);
    }
}
