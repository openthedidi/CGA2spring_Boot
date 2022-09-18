package com.cj.cga101g1.order.controller;


import com.cj.cga101g1.order.service.OrderService;
import com.cj.cga101g1.order.util.Order;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private Order order;


    @Transactional
    @PostMapping("addtoOrderAndOrderlist")
    public void addToOrderAndOrderList(@RequestBody Order order,
                                       @RequestBody OrderDetail orderDetail){
        order= orderService.createOrder(order);
        orderDetailService.createOne(order.getOrderNo(),orderDetail);
    }


}
