package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductUtil {

    public List<Object> pageCalculate(Integer page, String sql, Map<String,Object> mapQ, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                      GamePlatformTypeDAO gamePlatformTypeDAO, OrderDetailService orderDetailService){

        if(page<=0) {
            mapQ.put("Page",0);
        }else {
            mapQ.put("Page",(page-1)*9);
        }
        SqlRowSet sqlRowSet= namedParameterJdbcTemplate.queryForRowSet(sql,mapQ);
        List<Object> list = new ArrayList<>();
        while(sqlRowSet.next()){
            Map<String,Object> map = new HashMap<>();
            Integer productNo =(Integer) sqlRowSet.getObject(1);
            map.put("productNo", productNo);
            map.put("gameTypeNo", sqlRowSet.getObject(2));
            GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeDAO.getType(sqlRowSet.getInt(3));
            map.put("gamePlatformTypeName", gamePlatformTypeVO.getGamePlatformName());
            map.put("productName", sqlRowSet.getObject(5));
            map.put("productPrice", sqlRowSet.getObject(6));
            map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+sqlRowSet.getObject(1));
            Map<String,Object> orderDetailResult = orderDetailService.showCaledCommentByProductNo(productNo);
            map.put("avgCommentStar",orderDetailResult.get("avgCommentStar"));
            list.add(map);
        }
        return list;
    }
}
