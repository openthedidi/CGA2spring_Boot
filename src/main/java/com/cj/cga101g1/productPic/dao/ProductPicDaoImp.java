package com.cj.cga101g1.productPic.dao;

import com.cj.cga101g1.productPic.model.ProductPic;
import com.cj.cga101g1.productPic.util.ProductPicRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ProductPicDaoImp implements ProductPicDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductPicRowMapper productPicRowMapper;

    @Override
    public ProductPic getOneCoverByProductNo(Integer productNO) {
        final String GET_OneCover =
                "select ProductPicNO, ProductPicContent,ProductNo FROM productpic where ProductPicNO in(select min(ProductPicNO) FROM productpic group by ProductNo) and ProductNo = :ProductNo;";
        Map<String,Object> map = new HashMap<>();
        map.put("ProductNo",productNO);
        List<ProductPic> list = namedParameterJdbcTemplate.query(GET_OneCover,map,productPicRowMapper);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public ProductPic getOneCoverByProductPicNo(Integer ProductPicNo) {
        String sql = "select ProductPicNO, ProductPicContent,ProductNo from productpic where ProductPicNo = :ProductPicNo";
        Map<String,Object> map = new HashMap<>();
        map.put("ProductPicNo",ProductPicNo);
        List<ProductPic> list = namedParameterJdbcTemplate.query(sql,map,productPicRowMapper);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }
}
