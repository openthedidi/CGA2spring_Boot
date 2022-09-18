package com.cj.cga101g1.order.service;

import com.cj.cga101g1.order.dao.OrderDao;
import com.cj.cga101g1.order.util.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        return orderDao.createOrder(order);
    }
}
