package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;

public interface OrderDao {
    Order findOrderByOrderNo(Integer orderNo);
    Integer getMemNoByOrderNo(Integer orderNo);
}
