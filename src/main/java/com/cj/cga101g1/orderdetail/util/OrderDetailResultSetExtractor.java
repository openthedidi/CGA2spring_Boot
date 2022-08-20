package com.cj.cga101g1.orderdetail.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Component
public class OrderDetailResultSetExtractor implements ResultSetExtractor<Map<String,Object>> {
    @Override
    public Map<String,Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()){
            Map<String,Object>map = new HashMap<>();
            /**** 每個商品的全部星星數量 ****/
            map.put("totalCommentStars",rs.getInt("sum(CommentStar)"));
            /**** 每個商品的全部評論數量 ****/
            map.put("totalComments",rs.getInt("count(CommentStar)"));

            map.put("avgCommentStar", rs.getInt("sum(CommentStar)")/rs.getInt("count(CommentStar)"));
            return map;
        }else{
            Map<String,Object>map = new HashMap<>();
            map.put("totalCommentStars",0);
            map.put("totalComments",0);
            map.put("avgCommentStar",0);
            return map;
        }
    }
}
