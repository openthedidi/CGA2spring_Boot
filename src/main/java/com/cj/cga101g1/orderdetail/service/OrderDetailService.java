package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.orderdetail.util.OrderDetail;

import java.util.Map;

public interface OrderDetailService {
    Map<String,Object> showCaledCommentByProductNo(Integer productNo);

    void createOne(Integer orderNo, OrderDetail orderDetail);
}
