package com.example.order_service.messaging;

import java.math.BigDecimal;

public record FoodPayload(
        String foodId,
        String name,
        BigDecimal price
) {}
