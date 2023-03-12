package com.cj.cga101g1.orders.service;


import com.cj.cga101g1.memCoupon.service.MemCouponService;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.orders.dao.OrderDao;
import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private MemCouponService memCouponService;


    @Override
    public Order findOrderByOrderNo(Integer orderNo) {
        return orderDao.findOrderByOrderNo(orderNo);
    }

    @Transactional
    @Override
    public void addtoOrderAndOrderlist(Object memCouponNo, Integer orderTotalPrice, Integer pickupMethod,
                                       String city, String dist, String rod, String receiverName,
                                       String receiverPhone, Integer memNo, HttpSession session) {
        String memCouponStr = "".equals(memCouponNo) ? "0" : memCouponNo.toString();
        Integer couponTypeNo = memCouponNo == null? 0 : Integer.valueOf(memCouponStr);
                Integer shippingFee = (pickupMethod == 1 ? 1 : 0);
        String receiverAddress = city + dist + rod;
                /**  判斷有無使用優惠券  **/
        Integer newOrderNo = 0;
                if(couponTypeNo == 0){
                    newOrderNo = orderDao.newOrderWithoutCoupon(memNo, orderTotalPrice, pickupMethod, shippingFee, receiverName,
                            receiverAddress, receiverPhone);
                    System.out.println("newOrderNo: " + newOrderNo);
                }else {
                    newOrderNo = orderDao.newOrder(memNo, Integer.valueOf((String) memCouponNo), orderTotalPrice, pickupMethod, shippingFee, receiverName,
                            receiverAddress, receiverPhone);
                    System.out.println("newOderNo: " + newOrderNo);
                    /**   紀錄會員使用優惠券  **/
                    System.out.println("改變優惠券");
                    memCouponService.changeState(Integer.valueOf((String) memCouponNo));
                }
                List<CartDetail> cartDetailList = (List<CartDetail>) session.getAttribute("shoppingCart");
                orderDetailService.newOrderDetail(newOrderNo, cartDetailList);
                session.removeAttribute("shoppingCart");





    }

    @Override
    public List showAllOrderAndDetailsByMemNo(Integer memNo) {
        List<Order> orderList = orderDao.getAllByMemNo(memNo);
        List<Map> list = new ArrayList<>();
        java.text.DateFormat rule = new java.text.SimpleDateFormat("yyyy.MM.dd  '-' HH:mm:ss");
        for (Order order:orderList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderNo", order.getOrderNo().toString());
            map.put("memNo", memNo);
            Integer orderState = Integer.valueOf(order.getOrderState());
            map.put("orderState", orderState);
            if(orderState==0) {
                map.put("orderStateStr", "未出貨");
            }else if(orderState==1){
                map.put("orderStateStr", "已出貨");
            }else if(orderState==2){
                map.put("orderStateStr", "完成訂單");
            }else if(orderState==3){
                map.put("orderStateStr", "退貨");
            }else if(orderState==4){
                map.put("orderStateStr", "訂單作廢");
            }

            map.put("orderTotalPrice", order.getOrderTotalPrice().toString());
            map.put("pickupMethod", order.getPickupMethod().toString());
            map.put("tranTime", rule.format(order.getTranTime()));
            map.put("receiverName", order.getReceiverName());
            map.put("receiverAddress", order.getReceiverAddress());
            map.put("receiverPhone", order.getReceiverPhone());
            map.put("openOrderDetail", false);
            List<OrderDetail> orderDetailList = orderDetailService.getAllDetailByOrderNo(order.getOrderNo());
            map.put("orderDetail", orderDetailList);
            list.add(map);
        }


        return list;
    }
}
