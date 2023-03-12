package com.cj.cga101g1.orders.service;


import com.cj.cga101g1.orders.util.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public interface OrderService {
    Order findOrderByOrderNo(Integer orderNo);

    void addtoOrderAndOrderlist(Object memCouponNo, Integer orderTotalPrice, Integer pickupMethod, String city,
                                String dist, String rod, String receiverName, String receiverPhone, Integer memberNo, HttpSession session);

    List showAllOrderAndDetailsByMemNo(Integer memNo);
}
