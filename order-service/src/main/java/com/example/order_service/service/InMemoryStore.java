package com.example.order_service.service;

import com.example.order_service.messaging.FoodPayload;
import com.example.order_service.messaging.OrderPayload;
import com.example.order_service.messaging.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryStore {

    private final Map<String, FoodPayload> foods = new ConcurrentHashMap<>();
    private final Map<String, OrderPayload> orders = new ConcurrentHashMap<>();

    public void upsertFood(FoodPayload food) {
        foods.put(food.foodId(), food);
    }

    public void deleteFood(String foodId) {
        foods.remove(foodId);
    }

    public void createOrder(OrderPayload order) {
        orders.put(order.orderId(), order);
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        OrderPayload existing = orders.get(orderId);
        OrderPayload updated = new OrderPayload(orderId, existing == null ? null : existing.items(), status);
        orders.put(orderId, updated);
    }
}

