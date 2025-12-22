package com.example.caffe_main.messaging;

import java.util.List;

public record OrderPayload(
        String orderId,
        List<OrderItemPayload> items,
        OrderStatus status
) {}
