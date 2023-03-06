package com.cj.cga101g1.orders.service;


import com.cj.cga101g1.orders.dao.OrderDao;
import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;


    @Override
    public Order findOrderByOrderNo(Integer orderNo) {
        return orderDao.findOrderByOrderNo(orderNo);
    }
}
