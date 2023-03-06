package com.cj.cga101g1.memCoupon.dao;

import com.cj.cga101g1.memCoupon.util.MemCoupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemCouponRepository extends CrudRepository<MemCoupon,Integer> {
}
