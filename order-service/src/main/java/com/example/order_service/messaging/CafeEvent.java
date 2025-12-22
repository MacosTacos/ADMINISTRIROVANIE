package com.example.order_service.messaging;

public record CafeEvent(
        String eventId,
        EventType type,
        FoodPayload food,
        OrderPayload order
) {}
