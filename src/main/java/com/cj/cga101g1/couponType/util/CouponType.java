package com.cj.cga101g1.couponType.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
/**
 *  優惠券的種類包含不同名稱與折價
 *  ，資料庫table名稱為coupontype
 * */
@Getter
@Setter
@Entity
@Table(name = "coupontype")
@Component
public class CouponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CouponTypeNo")
    private Integer couponTypeNo;
    @Column(name = "CouponName")
    private String couponName;
    @Column(name = "DiscountPrice")
    private Integer discountPrice;
    @Column(name = "CouponDeadline")
    private Date couponDeadline;
    @Column(name = "CouponQuantity")
    private Integer couponQuantity;
    @Column(name = "CouponDescription")
    private String couponDescription;
}
