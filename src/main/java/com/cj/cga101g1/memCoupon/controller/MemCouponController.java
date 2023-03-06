package com.cj.cga101g1.memCoupon.controller;


import com.cj.cga101g1.memCoupon.service.MemCouponService;
import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CGA101G1/memCoupon")
public class MemCouponController {
    @Autowired
    private MemCouponService memCouponService;


    @GetMapping("findByMemCouponNo")
    public ResponseEntity<MemCoupon> findByMemCouponNo(@RequestParam Integer memCouponNo){
        return ResponseEntity.ok(memCouponService.findByMemNo(memCouponNo));
    }
}
