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
//        System.out.println("findOrderByOrderNo- orderNo : " + orderNo);
//        final String sql = "select * from cga101g1.orders where OrderNo = :orderNo ";
//        Map<String,Object> paramMap = new HashMap<>();
//        paramMap.put("orderNo",orderNo);
//        List<Order> list= namedParameterJdbcTemplate.query(sql, paramMap , ordersRowMapper);
//        System.out.println(list.get(0).getMemNo());
//        return list.get(0);
        return orderRepository.findById(orderNo).orElse(null);
    }


}
