package com.cj.cga101g1.orders.controller;

import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.orders.service.OrderService;
import com.cj.cga101g1.orders.util.Order;
import com.cj.cga101g1.util.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("CGA101G1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;

    @GetMapping("findOrderByOrderNo/{orderNo}")
    public ResponseEntity<Order> findOrderByOrderNo(@PathVariable Integer orderNo){
        return ResponseEntity.ok(orderService.findOrderByOrderNo(orderNo));
    }

    @PostMapping("addtoOrderAndOrderlist")
    public RedirectView addtoOrderAndOrderlist(@RequestParam("MemCouponNo") Object memCouponNo,
                                       @RequestParam("OrderTotalPrice")Integer orderTotalPrice,
                                       @RequestParam("PickupMethod")Integer pickupMethod,
                                       @RequestParam("city")String city,
                                       @RequestParam("dist")String dist,
                                       @RequestParam("Rod")String rod,
                                       @RequestParam("ReceiverName")String receiverName,
                                       @RequestParam("ReceiverPhone")String receiverPhone,
                                       @RequestParam("memNo") Integer memNo,
                                       HttpSession session) {
            orderService.addtoOrderAndOrderlist(memCouponNo, orderTotalPrice, pickupMethod, city,
                    dist, rod, receiverName, receiverPhone, memNo, session);
            return new RedirectView("/CGA101G1/frontend/Product/account-order.html");
    }

    @GetMapping("showAllOrderAndDetailsByMemNo")
    public ResponseEntity<List> showAllOrderAndDetailsByMemNo(@RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        if(jwtTokenUtils.validateToken(token)){
            System.out.println("驗證通過");
            Integer memNo = memberService.getMemByMemAccount(jwtTokenUtils.getUsername(token)).getMemNo();
            return ResponseEntity.ok(orderService.showAllOrderAndDetailsByMemNo(memNo));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

}
