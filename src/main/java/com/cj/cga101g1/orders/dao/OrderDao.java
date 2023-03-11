package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;

public interface OrderDao {
    Order findOrderByOrderNo(Integer orderNo);
    Integer getMemNoByOrderNo(Integer orderNo);

    Integer newOrder(Integer memNo, Integer memCouponNo, Integer orderTotalPrice, Integer pickupMethod, Integer shippingFee, String receiverName, String receiverAddress, String receiverPhone);

    Integer newOrderWithoutCoupon(Integer memNo, Integer orderTotalPrice, Integer pickupMethod, Integer shippingFee, String receiverName, String receiverAddress, String receiverPhone);
}
