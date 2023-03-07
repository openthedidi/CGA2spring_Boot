package com.cj.cga101g1.couponType.service;

import com.cj.cga101g1.couponType.dao.CouponTypeDao;
import com.cj.cga101g1.couponType.util.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CouponTypeServiceImp implements CouponTypeService{
    @Autowired
    private CouponTypeDao couponTypeDao;


    @Override
    public CouponType getCouponTypeByID(Integer couponTypeNo) {
        return couponTypeDao.getCouponTypeByID(couponTypeNo);
    }
}
