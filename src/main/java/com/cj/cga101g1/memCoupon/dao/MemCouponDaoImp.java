package com.cj.cga101g1.memCoupon.dao;

import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemCouponDaoImp implements MemCouponDao{

    @Autowired
    private MemCouponRepository memCouponRepository;


    @Override
    public MemCoupon findByMemNo(Integer memCouponNo) {
        return memCouponRepository.findById(memCouponNo).orElse(null);
    }
}
