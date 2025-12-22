package com.example.order_service.messaging;

import java.util.List;

public record OrderPayload(
        String orderId,
        List<OrderItemPayload> items,
        OrderStatus status
) {}
