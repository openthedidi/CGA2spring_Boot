package com.cj.cga101g1.order.service;

import com.cj.cga101g1.order.util.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {
    Order createOrder(Order order);
}
