package com.cj.cga101g1.orderdetail.dao;

import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetailResultSetExtractor;
import com.cj.cga101g1.orderdetail.util.OrderDetailRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDetailDaoImp implements OrderDetailDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private OrderDetailResultSetExtractor orderDetailResultSetExtractor;
    @Autowired
    private OrderDetail orderDetail;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailRowMapper orderDetailRowMapper;


    @Override
    public  Map<String,Object> showCaledCommentByProductNo(Integer productNo) {
        final String sql = "select ProductNo,sum(CommentStar),count(CommentStar) from cga101g1.orderdetail group by ProductNo having count(CommentStar)>0 and ProductNo = :ProductNo;";
        Map<String,Object>map = new HashMap<>();
        map.put("ProductNo",productNo);
        Map<String,Object>mapResult = namedParameterJdbcTemplate.query(sql,map,orderDetailResultSetExtractor);
        return mapResult;
    }

    @Override
    public List<OrderDetail> showOneProductAllComments(Integer productNo) {
        return orderDetailRepository.showOneProductAllComments(productNo);
    }

    @Override
    public List<OrderDetail> getCommentsInfosByOneProeduct(Integer productNo) {
        return orderDetailRepository.getCommentsInfosByOneProeduct(productNo);
    }

    @Override
    public void newOrderDetail(Integer newOrderNo, Integer productTotalPrice, Integer sales, String productNo) {
        orderDetailRepository.newOrderDetail(newOrderNo, productTotalPrice, sales, productNo);
    }

    @Override
    public List<OrderDetail> getAllDetailByOrderNo(Integer orderNo) {
        System.out.println(orderNo);
        final String sql = "select * from orderdetail where orderNo = :orderNo";
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        return namedParameterJdbcTemplate.query(sql,map ,orderDetailRowMapper);
    }

    @Override
    @Transactional
    public void addCommit(Integer productNo, Integer orderNo, String commentCotent, Integer commentStar) {
        orderDetailRepository.addCommit(productNo, orderNo, commentCotent, commentStar);
    }


}
