package com.example.order_service.listener;


import com.example.order_service.config.RabbitConfig;
import com.example.order_service.messaging.*;
import com.example.order_service.service.ActionPublisher;
import com.example.order_service.service.InMemoryStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CafeEventListener {

    private final InMemoryStore store;
    private final ActionPublisher publisher;

    public CafeEventListener(InMemoryStore store, ActionPublisher publisher) {
        this.store = store;
        this.publisher = publisher;
    }

    @RabbitListener(queues = RabbitConfig.CAFFE_EVENTS_QUEUE)
    public void handle(CafeEvent event) {
        switch (event.type()) {
            case FOOD_CREATED -> {
                store.upsertFood(event.food());
                publisher.publish(new ActionEvent(
                        UUID.randomUUID().toString(),
                        "FOOD_UPSERT",
                        event.food().foodId(),
                        "food created"
                ));
            }
            case FOOD_DELETED -> {
                store.deleteFood(event.food().foodId());
                publisher.publish(new ActionEvent(
                        UUID.randomUUID().toString(),
                        "FOOD_DELETE",
                        event.food().foodId(),
                        "food deleted"
                ));
            }
            case ORDER_CREATED -> {
                store.createOrder(event.order());
                publisher.publish(new ActionEvent(
                        UUID.randomUUID().toString(),
                        "ORDER_CREATE",
                        event.order().orderId(),
                        "order created"
                ));
            }
            case ORDER_STATUS_UPDATED -> {
                store.updateOrderStatus(event.order().orderId(), event.order().status());
                publisher.publish(new ActionEvent(
                        UUID.randomUUID().toString(),
                        "ORDER_STATUS_UPDATE",
                        event.order().orderId(),
                        "status updated to " + event.order().status()
                ));
            }
        }
    }
}
