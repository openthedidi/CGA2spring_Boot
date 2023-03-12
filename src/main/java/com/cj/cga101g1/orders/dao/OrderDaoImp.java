package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;
import com.cj.cga101g1.orders.util.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


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

    @Override
    public Integer newOrder(Integer memNo, Integer memCouponNo,
                            Integer orderTotalPrice, Integer pickupMethod, Integer shippingFee,
                            String receiverName, String receiverAddress, String receiverPhone) {
       orderRepository.newOrder(memNo, memCouponNo, orderTotalPrice, 0, pickupMethod, shippingFee, receiverName, receiverAddress, receiverPhone);
       return orderRepository.findMaxOrderNo();
    }

    @Override
    public Integer newOrderWithoutCoupon(Integer memNo, Integer orderTotalPrice,
                                         Integer pickupMethod, Integer shippingFee,
                                         String receiverName, String receiverAddress, String receiverPhone) {
        orderRepository.newOrderWithoutCoupon(memNo, orderTotalPrice, 0, pickupMethod, shippingFee, receiverName, receiverAddress, receiverPhone);
        return orderRepository.findMaxOrderNo();
    }

    @Override
    public List<Order> getAllByMemNo(Integer memNo) {
        return orderRepository.getAllByMemNo(memNo);
    }


}
