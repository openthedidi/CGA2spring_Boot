package com.cj.cga101g1.orders.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *  會員下的訂單，資料庫table名稱為order
 * */
@Getter
@Setter
@Component
@Entity
@Table(name = "order",schema = "cga101g1")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderNo")
    private Integer orderNo;
    @Column(name = "MemNo")
    private Integer memNo;
    @Column(name = "MemCouponNo")
    private Integer memCouponNo;
    @Column(name = "TranTime")
    private Timestamp tranTime;
    @Column(name = "OrderTotalPrice")
    private Integer orderTotalPrice;
    @Column(name = "OrderState")
    private Integer orderState;
    @Column(name = "PickupMethod")
    private Integer pickupMethod;
    @Column(name = "ShippingFee")
    private Integer shippingFee;
    @Column(name = "TrackingNum")
    private String trackingNum;
    @Column(name = "ReceiverName")
    private String receiverName;
    @Column(name = "ReceiverAddress")
    private String receiverAddress;
    @Column(name = "ReceiverPhone")
    private String receiverPhone;
    @Column(name = "PickupTime")
    private Date pickupTime;

}
