package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.model.ProductResultSetExtractor;
import com.cj.cga101g1.product.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.util.*;

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
        return result.toString();
    }

    @Override
    public List<Object> getPageInSellByMap(Integer page) {
        final String sql = "SELECT a.productNo,a.gameTypeNo,a.gamePlatformNo,a.productName,a.productPrice,b.GamePlatformName FROM product a  "
                + "join gameplatformtype b on a.gamePlatformNo = b.gamePlatformNo "
                + "where ProductState = 1 order by productNo desc limit :page ,9 ";
        Map<String,Object> mapQ = new HashMap<>();
        if(page<=0) {
            mapQ.put("page",0);
        }else {
            mapQ.put("page",(page-1)*9);
        }
        SqlRowSet sqlRowSet= namedParameterJdbcTemplate.queryForRowSet(sql,mapQ);
        List<Object> list = new ArrayList<>();
        while(sqlRowSet.next()){
            Map<String,Object> map = new HashMap<>();
            map.put("productNo", sqlRowSet.getObject(1));
            map.put("gameTypeNo", sqlRowSet.getObject(2));
            map.put("gamePlatformTypeName", sqlRowSet.getObject(6));
            map.put("productName", sqlRowSet.getObject(4));
            map.put("productPrice", sqlRowSet.getObject(5));
            map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+sqlRowSet.getObject(1));
            list.add(map);
        }


        return list;
    }
}
