package com.example.order_service.messaging;


public record OrderItemPayload(
        String foodId,
        int quantity
) {}
