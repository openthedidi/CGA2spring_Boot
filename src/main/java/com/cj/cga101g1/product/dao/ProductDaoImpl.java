package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAOImp;
import com.cj.cga101g1.gameplatformtype.util.GamePlatformTypeVO;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.model.ProductResultSetExtractor;
import com.cj.cga101g1.product.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Component
public class ProductDaoImpl implements  ProductDao{

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductResultSetExtractor productResultSetExtractor;
    @Autowired
    private OrderDetailService orderDetailService;

    final private String ShowInSellCount =
            "SELECT count(productNo) FROM product where ProductState = 1 ;";

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
            Integer productNo =(Integer) sqlRowSet.getObject(1);
            map.put("productNo", productNo);
            map.put("gameTypeNo", sqlRowSet.getObject(2));
            map.put("gamePlatformTypeName", sqlRowSet.getObject(6));
            map.put("productName", sqlRowSet.getObject(4));
            map.put("productPrice", sqlRowSet.getObject(5));
            map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+sqlRowSet.getObject(1));
            Map<String,Object> orderDetailResult = orderDetailService.showCaledCommentByProductNo(productNo);
            map.put("avgCommentStar",orderDetailResult.get("avgCommentStar"));
            list.add(map);
        }


        return list;
    }

    @Override
    public String showSelledCountByPlatFormType(Integer gamePlatformNo) {
        String appendSql = "and GamePlatformNo = :gamePlatformNo ;";
        StringBuilder stringBuilder = new StringBuilder(ShowInSellCount);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(appendSql);
        Map<String,Object> map = new HashMap<>();
        map.put("gamePlatformNo",gamePlatformNo);
        Integer result=(Integer) namedParameterJdbcTemplate.query(stringBuilder.toString(),map,productResultSetExtractor);
        return result.toString();
    }

    @Override
    public List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page) {
        final String sql =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 and GamePlatformNo = :gamePlatformNo order by productNo desc limit  :Page , 9;";
        Map<String,Object> mapQ = new HashMap<>();
        mapQ.put("gamePlatformNo",gamePlatformNo);
        if(Page<=0) {
            mapQ.put("Page",0);
        }else {
            mapQ.put("Page",(Page-1)*9);
        }
        SqlRowSet sqlRowSet= namedParameterJdbcTemplate.queryForRowSet(sql,mapQ);
        List<Object> list = new ArrayList<>();
        while(sqlRowSet.next()){
            Map<String,Object> map = new HashMap<>();
            Integer productNo =(Integer) sqlRowSet.getObject(1);
            map.put("productNo", productNo);
            map.put("gameTypeNo", sqlRowSet.getObject(2));
//            GamePlatformTypeDAO gamePlatformTypeDAO = new GamePlatformTypeDAOImp();
//            GamePlatformTypeVO gamePlatformTypeVO = gamePlatformTypeDAO.getType(sqlRowSet.getInt(3));
//            map.put("gamePlatformTypeName", gamePlatformTypeVO.getGamePlatformName());
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
