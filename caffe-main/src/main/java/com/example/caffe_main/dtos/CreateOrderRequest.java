package com.example.caffe_main.dtos;

import java.util.List;

public record CreateOrderRequest(
        List<Item> items
) {
    public record Item(String foodId, int quantity) {}
}
