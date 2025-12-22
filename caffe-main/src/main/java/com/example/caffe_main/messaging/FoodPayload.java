package com.example.caffe_main.messaging;

import java.math.BigDecimal;

public record FoodPayload(
        String foodId,
        String name,
        BigDecimal price
) {}
