package com.cj.cga101g1.memCoupon.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@Component
@Entity
@Table(name = "memcoupon")
public class MemCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemCouponNo")
    private Integer memCouponNo;
    @Column(name = "CouponTypeNo")
    private Integer couponTypeNo;
    @Column(name = "MemNo")
    private Integer memNo;
    @Column(name = "CouponState")
    private Integer couponState;
    @Column(name = "CouponDate")
    private Date couponDate;
}
