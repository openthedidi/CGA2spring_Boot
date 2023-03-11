package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDaoImpTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Order order;

    @Test
    void newOrder() {
        Integer newOrderNo =  orderDao.newOrder(11001,12001,1200,1,1,"Tom","8街","098554555");
        assertNotNull(newOrderNo);
    }

    @Test
    void newOrderWithoutCoupon() {
    }
}