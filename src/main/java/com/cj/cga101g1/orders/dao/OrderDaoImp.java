package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;
import com.cj.cga101g1.orders.util.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImp implements OrderDao{

    @Autowired
    @Qualifier("orderRepository")
    private OrderRepository orderRepository;

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private OrdersRowMapper ordersRowMapper;

    @Override
    public Order findOrderByOrderNo(Integer orderNo) {
        return orderRepository.findById(orderNo).orElse(null);
    }

    @Override
    public Integer getMemNoByOrderNo(Integer orderNo) {
        return orderRepository.getMemNoByOrderNo(orderNo);
    }


}
