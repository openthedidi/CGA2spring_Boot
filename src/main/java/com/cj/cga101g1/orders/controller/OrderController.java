package com.cj.cga101g1.orders.controller;

import com.cj.cga101g1.orders.service.OrderService;
import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CGA101G1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("findOrderByOrderNo/{orderNo}")
    public ResponseEntity<Order> findOrderByOrderNo(@PathVariable Integer orderNo){
        return ResponseEntity.ok(orderService.findOrderByOrderNo(orderNo));
    }



}
