package com.cj.cga101g1.orders.dao;

import com.cj.cga101g1.orders.util.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrdersRowMapper implements RowMapper {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderNo(rs.getInt("OrderNo"));
        order.setMemNo(rs.getInt("MemNo"));
        order.setMemCouponNo(rs.getInt("MemCouponNo"));
        order.setTranTime(rs.getTimestamp("TranTime"));
        order.setOrderTotalPrice(rs.getInt("OrderTotalPrice"));
        order.setOrderState(rs.getInt("OrderState"));
        order.setPickupMethod(rs.getInt("PickupMethod"));
        order.setShippingFee(rs.getInt("ShippingFee"));
        order.setTrackingNum(rs.getString("TrackingNum"));
        order.setReceiverName(rs.getString("ReceiverName"));
        order.setReceiverAddress(rs.getString("ReceiverAddress"));
        order.setReceiverPhone(rs.getString("ReceiverPhone"));
        order.setPickupTime(rs.getDate("PickupTime"));
        return order;
    }
}
