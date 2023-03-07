package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetail;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    Map<String,Object> showCaledCommentByProductNo(Integer productNo);

    void createOne(Integer orderNo, OrderDetail orderDetail);

    List<CartDetail> setShoppingCart(HttpSession session, String productNo, Integer productSales, Integer productTotalPrice, String productName);

    List<Object> showOneProductAllComments(Integer productNo);

    Map showProductCaledComment(Integer productNo);
}
