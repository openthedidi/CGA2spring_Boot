package com.cj.cga101g1.orderdetail.util;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {
    @Override
    public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCommentCotent(rs.getNString("CommentCotent"));
        orderDetail.setOrderNo(rs.getInt("OrderNo"));
        orderDetail.setCommentStar(rs.getInt("CommentStar"));
        orderDetail.setCommentTime(rs.getDate("CommentTime"));
        orderDetail.setProductTotalPrice(rs.getInt("ProductTotalPrice"));
        orderDetail.setProductSales(rs.getInt("ProductSales"));
        orderDetail.setProductNo(rs.getInt("ProductNo"));
        return orderDetail;
    }
}
