package com.cj.cga101g1.orderdetail.util;


import com.cj.cga101g1.orders.controller.OrderController;
import com.cj.cga101g1.orders.dao.OrderDaoImp;
import com.cj.cga101g1.orders.service.OrderService;
import com.cj.cga101g1.orders.service.OrderServiceImp;
import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "orderdetail",schema = "cga101g1")
@Component
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderNo")
    private Integer orderNo;
    @Column(name = "ProductNo")
    private Integer productNo;
    private Integer productSales;
    private Integer productTotalPrice;
    private String commentCotent;
    private Date commentTime;
    private Integer CommentStar;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public Integer getProductSales() {
        return productSales;
    }

    public void setProductSales(Integer productSales) {
        this.productSales = productSales;
    }

    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Integer productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getCommentCotent() {
        return commentCotent;
    }

    public void setCommentCotent(String commentCotent) {
        this.commentCotent = commentCotent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentStar() {
        return CommentStar;
    }

    public void setCommentStar(Integer commentStar) {
        CommentStar = commentStar;
    }



    public Order findOrderObjByOrderNo(Integer orderNo){
        System.out.println(" findOrderObjByOrderNo -orderNo: " + orderNo);
        OrderController orderController = new OrderController();
        ResponseEntity<Order> responseEntity = orderController.findOrderByOrderNo(orderNo);
        return responseEntity.getBody();
    }
}
