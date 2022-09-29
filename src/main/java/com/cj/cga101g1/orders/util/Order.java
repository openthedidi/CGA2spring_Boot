package com.cj.cga101g1.orders.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Component
public class Order {

    private Integer orderNo;
    private Integer memNo;
    private Integer memCouponNo;
    private Timestamp tranTime;
    private Integer orderTotalPrice;
    private Integer orderState;
    private Integer pickupMethod;
    private Integer shippingFee;
    private String trackingNum;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private Date pickupTime;
}
