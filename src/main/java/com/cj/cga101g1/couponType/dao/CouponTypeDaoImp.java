package com.cj.cga101g1.couponType.dao;

import com.cj.cga101g1.couponType.util.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponTypeDaoImp implements CouponTypeDao{
    @Autowired
    private CouponTypeRepository couponTypeRepository;


    @Override
    public CouponType getCouponTypeByID(Integer couponTypeNo) {
        return couponTypeRepository.findById(couponTypeNo).orElse(null);
    }
}
