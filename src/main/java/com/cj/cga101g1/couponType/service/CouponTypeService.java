package com.cj.cga101g1.couponType.service;

import com.cj.cga101g1.couponType.util.CouponType;

public interface CouponTypeService {
    /** 利用couponTypeNo取得CouponType的pojo **/
    CouponType getCouponTypeByID(Integer couponTypeNo);
}
