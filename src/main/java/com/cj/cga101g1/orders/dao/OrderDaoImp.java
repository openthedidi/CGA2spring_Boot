package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImp implements OrderDao{



    @Override
    public Order findByID(Integer orderNo) {

        return null;
    }
}
