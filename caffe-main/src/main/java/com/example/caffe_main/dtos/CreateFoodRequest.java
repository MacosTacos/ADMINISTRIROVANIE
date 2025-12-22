package com.example.caffe_main.dtos;

import java.math.BigDecimal;

public record CreateFoodRequest(
        String name,
        BigDecimal price
) {}
