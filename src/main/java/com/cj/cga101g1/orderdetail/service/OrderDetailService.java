package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetail;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    List<CartDetail> reduceCart(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName) ;

    Map<String,Object> showCaledCommentByProductNo(Integer productNo);

    void createOne(Integer orderNo, OrderDetail orderDetail);

    List<CartDetail> setShoppingCart(HttpSession session, String productNo, Integer productSales, Integer productTotalPrice, String productName);

    List<Object> showOneProductAllComments(Integer productNo);

    Map showProductCaledComment(Integer productNo);

    void addCart(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName);

    void shoppingCartRemoveAll(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName);

    void newOrderDetail(Integer newOrderNo, List<CartDetail> cartDetailList);

    List<OrderDetail> getAllDetailByOrderNo(Integer orderNo);

    void addCommit(Integer productNo, Integer orderNo, String commentCotent, Integer commentStar);
}
