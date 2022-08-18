package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.model.ProductResultSetExtractor;
import com.cj.cga101g1.product.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements  ProductDao{

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductResultSetExtractor productResultSetExtractor;


    @Override
    public Product findByPrimaryKey(Integer productNo) {
        final String GET_ONE =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo," +
                        "productName,productPrice,productState,itemProdDescription,upcNum " +
                        "FROM product where productNo = :productNo";

        Map<String,Object> map = new HashMap<>();
        map.put("productNo",productNo);

        List<Product>list = namedParameterJdbcTemplate.query(GET_ONE,map,new ProductRowMapper());
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public String showSelledCount() {
         final String ShowInSellCount =
                "SELECT count(productNo) FROM product where ProductState = 1 ;";
        Integer result=(Integer) namedParameterJdbcTemplate.query(ShowInSellCount,productResultSetExtractor);
        System.out.println(result);
        return result.toString();
    }
}
