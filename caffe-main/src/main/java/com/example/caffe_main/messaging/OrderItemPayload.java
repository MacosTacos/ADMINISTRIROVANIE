package com.example.caffe_main.messaging;

public record OrderItemPayload(
        String foodId,
        int quantity
) {}
