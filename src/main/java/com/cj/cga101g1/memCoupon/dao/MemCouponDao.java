package com.cj.cga101g1.memCoupon.dao;

import com.cj.cga101g1.memCoupon.util.MemCoupon;

import java.util.List;


public interface MemCouponDao {

    MemCoupon findByMemNo(Integer memCouponNo);

    List<MemCoupon> getMemAllCoupons(Integer memNo);

    void changeState(Integer memCouponNo);
}
