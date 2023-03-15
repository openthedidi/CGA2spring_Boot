package com.cj.cga101g1.couponType.dao;


import com.cj.cga101g1.couponType.util.CouponType;


public interface CouponTypeDao {
    /** 利用couponTypeNo取得CouponType的pojo **/
    CouponType getCouponTypeByID(Integer couponTypeNo);
}
