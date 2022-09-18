package com.cj.cga101g1.order.dao;

import com.cj.cga101g1.order.util.Order;
import com.cj.cga101g1.order.util.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImp implements OrderDao{
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
