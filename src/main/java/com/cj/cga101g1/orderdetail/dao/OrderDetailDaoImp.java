package com.cj.cga101g1.orderdetail.dao;

import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetailResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDetailDaoImp implements OrderDetailDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private OrderDetailResultSetExtractor orderDetailResultSetExtractor;
    @Autowired
    private OrderDetail orderDetail;


    @Override
    public  Map<String,Object> showCaledCommentByProductNo(Integer productNo) {
        final String sql = "select ProductNo,sum(CommentStar),count(CommentStar) from cga101g1.orderdetail group by ProductNo having count(CommentStar)>0 and ProductNo = :ProductNo;";
        Map<String,Object>map = new HashMap<>();
        map.put("ProductNo",productNo);
        Map<String,Object>mapResult = namedParameterJdbcTemplate.query(sql,map,orderDetailResultSetExtractor);
        return mapResult;
    }
}
