package com.example.caffe_main.dtos;

import com.example.caffe_main.messaging.OrderStatus;

public record UpdateOrderStatusRequest(
        OrderStatus status
) {}
