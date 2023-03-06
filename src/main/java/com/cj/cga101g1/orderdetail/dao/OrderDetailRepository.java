package com.cj.cga101g1.orderdetail.dao;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer> {

    @Query(value =
            "select ProductNo,ProductSales, ProductTotalPrice,CommentCotent,CommentTime, CommentStar,OrderNo FROM orderdetail WHERE ProductNo = ?1 and CommentStar >= 0 order by CommentTime desc"
            ,nativeQuery = true)
    List<OrderDetail> showOneProductAllComments(Integer productNo);
}
