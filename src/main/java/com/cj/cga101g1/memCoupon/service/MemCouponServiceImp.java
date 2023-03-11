package com.cj.cga101g1.memCoupon.service;

import com.cj.cga101g1.couponType.service.CouponTypeService;
import com.cj.cga101g1.couponType.service.CouponTypeServiceImp;
import com.cj.cga101g1.couponType.util.CouponType;
import com.cj.cga101g1.memCoupon.dao.MemCouponDao;
import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemCouponServiceImp implements MemCouponService{

    @Autowired
    private MemCouponDao memCouponDao;

    @Autowired
    private CouponTypeService couponTypeService;

    @Override
    public MemCoupon findByMemNo(Integer memCouponNo) {
        return memCouponDao.findByMemNo(memCouponNo);
    }

    @Override
    public List<Object> getMemAllCoupons(Integer memNo) {
       List<MemCoupon> listResult = memCouponDao.getMemAllCoupons(memNo);
        List<Object> list = new ArrayList<Object>();
        for (MemCoupon memCoupon : listResult) {
            Map<String,Object> map = new HashMap<>();
            map.put("memNo", memNo);
            Integer couponTypeNo = memCoupon.getCouponTypeNo();
            CouponType couponType = couponTypeService.getCouponTypeByID(couponTypeNo);
            map.put("couponTypeNo", couponTypeNo);
            Integer memCouponNo = memCoupon.getMemCouponNo();
            map.put("memCouponNo", memCouponNo);
            Integer couponState = memCoupon.getCouponState();
            map.put("couponState", couponState);
            Integer discountPrice = couponType.getDiscountPrice();
            map.put("discountPrice", discountPrice);
            list.add(map);
        }
        return list;
    }

    @Override
    public void changeState(Integer memCouponNo) {
        memCouponDao.changeState(memCouponNo);
    }
}
