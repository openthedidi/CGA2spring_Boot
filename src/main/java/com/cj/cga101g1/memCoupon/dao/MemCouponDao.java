package com.cj.cga101g1.memCoupon.dao;

import com.cj.cga101g1.memCoupon.util.MemCoupon;


public interface MemCouponDao {

    MemCoupon findByMemNo(Integer memCouponNo);
}
