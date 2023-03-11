package com.cj.cga101g1.memCoupon.service;

import com.cj.cga101g1.memCoupon.util.MemCoupon;

import java.util.List;
import java.util.Map;

public interface MemCouponService {
    MemCoupon findByMemNo(Integer memCouponNo);

    List<Object> getMemAllCoupons(Integer memNo);

    void changeState(Integer memCouponNo);
}
