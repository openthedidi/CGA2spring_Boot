package com.cj.cga101g1.orders.service;


import com.cj.cga101g1.orders.util.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {
    Order findOrderByOrderNo(Integer orderNo);
}
