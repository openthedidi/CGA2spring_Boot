package com.cj.cga101g1.orders.util;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Query(value = "select * from cga101g1.order where OrderNo = ?1",nativeQuery = true)
    Order findOrderByOrderNo(Integer orderNo);

    @Query(value = "select MEMNO from cga101g1.order where OrderNo = ?1",nativeQuery = true)
    Integer getMemNoByOrderNo(Integer orderNo);

    @Query(value = "SELECT max(orderNo) FROM cga101g1.order",nativeQuery = true)
    Integer findMaxOrderNo();

    @Modifying
    @Query(value = "INSERT INTO cga101g1.order (MemNo,MemCouponNo,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,ReceiverName,ReceiverAddress,ReceiverPhone) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9)",nativeQuery = true)
    void newOrder(Integer memNo, Integer memCouponNo, Integer orderTotalPrice, Integer orderState, Integer pickupMethod, Integer shippingFee, String receiverName, String receiverAddress, String receiverPhone);

    @Modifying
    @Query(value = "INSERT INTO cga101g1.order (MemNo,OrderTotalPrice,OrderState,PickupMethod,ShippingFee,ReceiverName,ReceiverAddress,ReceiverPhone) VALUES (?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery= true)
    void newOrderWithoutCoupon(Integer memNo, Integer orderTotalPrice, Integer orderState, Integer pickupMethod, Integer shippingFee, String receiverName, String receiverAddress, String receiverPhone);

    @Query(value = "select * from cga101g1.order where memNo = ?1 order by TranTime desc",nativeQuery = true)
    List<Order> getAllByMemNo(Integer memNo);
}
