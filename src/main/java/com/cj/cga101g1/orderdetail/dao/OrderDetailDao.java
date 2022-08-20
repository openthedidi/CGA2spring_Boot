package com.cj.cga101g1.orderdetail.dao;

import com.cj.cga101g1.orderdetail.util.OrderDetail;

import java.util.Map;

public interface OrderDetailDao {
    Map<String,Object> showCaledCommentByProductNo(Integer productNo);
}
