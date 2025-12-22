package com.example.caffe_main.controller;


import com.example.caffe_main.dtos.CreateFoodRequest;
import com.example.caffe_main.messaging.CafeEvent;
import com.example.caffe_main.messaging.EventType;
import com.example.caffe_main.messaging.FoodPayload;
import com.example.caffe_main.services.CafePublisher;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Food")
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final CafePublisher publisher;

    public FoodController(CafePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public FoodPayload createFood(@RequestBody CreateFoodRequest request) {
        String foodId = UUID.randomUUID().toString();

        FoodPayload food = new FoodPayload(foodId, request.name(), request.price());
        CafeEvent event = new CafeEvent(
                UUID.randomUUID().toString(),
                EventType.FOOD_CREATED,
                food,
                null
        );

        publisher.publish(event);
        return food;
    }

    @DeleteMapping("/{foodId}")
    public String deleteFood(@PathVariable String foodId) {
        FoodPayload food = new FoodPayload(foodId, null, null);
        CafeEvent event = new CafeEvent(
                UUID.randomUUID().toString(),
                EventType.FOOD_DELETED,
                food,
                null
        );

        publisher.publish(event);
        return "OK";
    }
}
