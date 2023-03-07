package com.cj.cga101g1.memCoupon.controller;


import com.cj.cga101g1.memCoupon.service.MemCouponService;
import com.cj.cga101g1.memCoupon.util.MemCoupon;
import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.util.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CGA101G1/memCoupon")
public class MemCouponController {
    @Autowired
    private MemCouponService memCouponService;

    @Autowired
    private MemberService memberService;

    @GetMapping("findByMemCouponNo")
    public ResponseEntity<MemCoupon> findByMemCouponNo(@RequestParam Integer memCouponNo){
        return ResponseEntity.ok(memCouponService.findByMemNo(memCouponNo));
    }

    @GetMapping("/getMemAllCoupons")
    public ResponseEntity<List> getMemAllCoupons(@RequestHeader("Authorization") String token) throws AuthException {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        if(jwtTokenUtils.validateToken(token)){
            String memAccount = jwtTokenUtils.getUsername(token);
            Integer memNo = memberService.getMemByMemAccount(memAccount).getMemNo();
            List list = memCouponService.getMemAllCoupons(memNo);
            return ResponseEntity.ok(list);
        }else {
            System.out.println("驗證失敗");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
