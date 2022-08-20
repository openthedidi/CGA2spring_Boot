package com.cj.cga101g1.orderdetail.controller;


import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("CGA101G1/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    /*****  查詢每個商品的總評論數量及總評論星星數量  *****/
    @GetMapping("/comments")
    public ResponseEntity<Map<String,Object>> comments(@RequestParam Integer productNo){
        Map<String,Object>map = orderDetailService.showCaledCommentByProductNo(productNo);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }



}
