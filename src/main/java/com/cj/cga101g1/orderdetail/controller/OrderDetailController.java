package com.cj.cga101g1.orderdetail.controller;


import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.util.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CGA101G1/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    /**  查詢每個商品的總評論數量及總評論星星數量  *****/
    @GetMapping("/comments")
    public ResponseEntity<Map<String,Object>> comments(@RequestParam Integer productNo){
        Map<String,Object>map = orderDetailService.showCaledCommentByProductNo(productNo);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }


    /** 商品放入購物車，session attribute : shoppingCart  *****/
    @GetMapping("/add2ShoppingCart")
    public ResponseEntity<List<CartDetail>> add2ShoppingCart(HttpSession session,
                                                             @RequestParam String productNo,
                                                             @RequestParam Integer productSales,
                                                             @RequestParam Integer productTotalPrice,
                                                             @RequestParam String productName){
        List<CartDetail> cartDetailList = orderDetailService.setShoppingCart(session, productNo, productSales, productTotalPrice, productName);
        return ResponseEntity.ok(cartDetailList);
    }


    @GetMapping("/showOneProductAllComments")
    public ResponseEntity<List<Object>> showOneProductAllComments(@RequestParam Integer productNo){
        List<Object> list = orderDetailService.showOneProductAllComments(productNo);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/showProductCaledComment")
    public ResponseEntity<Map> showProductCaledComment(@RequestParam Integer productNo){
        Map map = orderDetailService.showProductCaledComment(productNo);
        return ResponseEntity.ok(map);
    }

    @GetMapping("showCart")
    public ResponseEntity<List> showCart(HttpSession session,
                                               @RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        if(jwtTokenUtils.validateToken(token)){
            List list = (List) session.getAttribute("shoppingCart");
            return ResponseEntity.ok(list);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }

    }
}
