package com.example.order_service.config;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String CAFFE_EVENTS_QUEUE = "caffe.events.queue";
    public static final String ORDER_ACTIONS_QUEUE = "order.actions.queue";

    @Bean
    public Queue caffeEventsQueue() {
        return new Queue(CAFFE_EVENTS_QUEUE, false);
    }

    @Bean
    public Queue orderActionsQueue() {
        return new Queue(ORDER_ACTIONS_QUEUE, false);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        var template = new org.springframework.amqp.rabbit.core.RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}

