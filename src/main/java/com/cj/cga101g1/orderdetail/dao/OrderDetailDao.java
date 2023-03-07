package com.cj.cga101g1.orderdetail.dao;

import com.cj.cga101g1.orderdetail.util.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDetailDao {
    Map<String,Object> showCaledCommentByProductNo(Integer productNo);

    List<OrderDetail> showOneProductAllComments(Integer productNo);

    List<OrderDetail> getCommentsInfosByOneProeduct(Integer productNo);
}
