package com.example.caffe_main.controller;

import com.example.caffe_main.dtos.CreateOrderRequest;
import com.example.caffe_main.dtos.UpdateOrderStatusRequest;
import com.example.caffe_main.messaging.*;
import com.example.caffe_main.services.CafePublisher;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Order")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CafePublisher publisher;

    public OrderController(CafePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public OrderPayload createOrder(@RequestBody CreateOrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        List<OrderItemPayload> items = request.items().stream()
                .map(i -> new OrderItemPayload(i.foodId(), i.quantity()))
                .toList();

        OrderPayload order = new OrderPayload(orderId, items, OrderStatus.NEW);

        CafeEvent event = new CafeEvent(
                UUID.randomUUID().toString(),
                EventType.ORDER_CREATED,
                null,
                order
        );

        publisher.publish(event);
        return order;
    }

    @PutMapping("/{orderId}/status")
    public String updateStatus(@PathVariable String orderId, @RequestBody UpdateOrderStatusRequest request) {
        OrderPayload order = new OrderPayload(orderId, null, request.status());

        CafeEvent event = new CafeEvent(
                UUID.randomUUID().toString(),
                EventType.ORDER_STATUS_UPDATED,
                null,
                order
        );

        publisher.publish(event);
        return "OK";
    }
}
