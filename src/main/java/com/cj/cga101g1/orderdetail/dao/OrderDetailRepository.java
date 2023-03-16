package com.cj.cga101g1.orderdetail.dao;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer> {

    @Query(value =
            "select ProductNo,ProductSales, ProductTotalPrice,CommentCotent,CommentTime, CommentStar,OrderNo FROM orderdetail WHERE ProductNo = ?1 and CommentStar >= 0 order by CommentTime desc"
            ,nativeQuery = true)
    List<OrderDetail> showOneProductAllComments(Integer productNo);

//    @Query(value =
//            "select * FROM orderdetail WHERE ProductNo = ?1 and CommentStar >= 0 order by CommentTime desc"
//            ,nativeQuery = true)
    @Procedure(name = "getCommentsInfosByOneProeduct")
    List<OrderDetail> getCommentsInfosByOneProeduct(@Param("productNo") Integer productNo);

    @Modifying
    @Query(value = "INSERT INTO orderdetail (OrderNo, ProductNo, ProductSales, ProductTotalPrice) VALUES (?1, ?4, ?3, ?2)", nativeQuery = true)
    void newOrderDetail(Integer newOrderNo, Integer productTotalPrice, Integer sales, String productNo);


    @Modifying
    @Query(value = "UPDATE orderdetail SET CommentCotent = ?3, CommentTime = now(),  CommentStar = ?4 WHERE (OrderNo = ?2) and (ProductNo = ?1 )", nativeQuery = true)
    void addCommit(Integer productNo, Integer orderNo, String commentCotent, Integer commentStar);
}
