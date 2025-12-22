package com.example.caffe_main.messaging;

public record CafeEvent(
        String eventId,
        EventType type,
        FoodPayload food,
        OrderPayload order
) {}
