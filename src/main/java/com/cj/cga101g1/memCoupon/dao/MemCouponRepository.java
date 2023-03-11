package com.cj.cga101g1.memCoupon.dao;

import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemCouponRepository extends CrudRepository<MemCoupon,Integer> {
    @Query(value = "select * from memcoupon where memNo = ?1",nativeQuery = true)
    List<MemCoupon> getMemAllCoupons(Integer memNo);

    @Modifying
    @Query(value = "UPDATE memcoupon SET couponState=2 WHERE memCouponNo = ?1 ", nativeQuery = true)
    void changeState(Integer memCouponNo);
}
