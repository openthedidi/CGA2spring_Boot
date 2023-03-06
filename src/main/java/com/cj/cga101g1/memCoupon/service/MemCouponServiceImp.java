package com.cj.cga101g1.memCoupon.service;

import com.cj.cga101g1.memCoupon.dao.MemCouponDao;
import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemCouponServiceImp implements MemCouponService{

    @Autowired
    private MemCouponDao memCouponDao;


    @Override
    public MemCoupon findByMemNo(Integer memCouponNo) {
        return memCouponDao.findByMemNo(memCouponNo);
    }
}
