package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.model.ProductResultSetExtractor;
import com.cj.cga101g1.product.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
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
    @Autowired
    private  GamePlatformTypeDAO gamePlatformTypeDAO;
    @Autowired
    private  ProductUtil productUtil;

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
                + "where ProductState = 1 order by productNo desc limit :Page ,9 ";
        Map<String,Object> mapQ = new HashMap<>();
        return productUtil.pageCalculate(page,sql,mapQ,namedParameterJdbcTemplate,gamePlatformTypeDAO,orderDetailService);
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
    public List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer page) {
        final String sql =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 and GamePlatformNo = :gamePlatformNo order by productNo desc limit  :Page , 9;";
        Map<String,Object> mapQ = new HashMap<>();
        mapQ.put("gamePlatformNo",gamePlatformNo);
        return productUtil.pageCalculate(page,sql,mapQ,namedParameterJdbcTemplate,gamePlatformTypeDAO,orderDetailService);
    }

    @Override
    public String showSellAndGameTypeProductPages(Integer gameTypeNo) {
        String appendSql = "and gameTypeNo = :gameTypeNo ;";
        StringBuilder stringBuilder = new StringBuilder(ShowInSellCount);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(appendSql);
        Map<String,Object> map = new HashMap<>();
        map.put("gameTypeNo",gameTypeNo);
        Integer result=(Integer) namedParameterJdbcTemplate.query(stringBuilder.toString(),map,productResultSetExtractor);
        return result.toString();
    }

    @Override
    public List<Object> showSellAndGameTypeProduct(Integer gameTypeNo, Integer page) {
        final String sql =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 and gameTypeNo = :gameTypeNo order by productNo desc limit  :Page , 9;";
        Map<String,Object> mapQ = new HashMap<>();
        mapQ.put("gameTypeNo",gameTypeNo);
        return productUtil.pageCalculate(page,sql,mapQ,namedParameterJdbcTemplate,gamePlatformTypeDAO,orderDetailService);
    }

    @Override
    public String showSellCountByMoney(Integer lowPrice, Integer highPrice) {
        String appendSql = "and productPrice between :lowPrice and :highPrice";
        StringBuilder stringBuilder = new StringBuilder(ShowInSellCount);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(appendSql);
        Map<String,Object> map = new HashMap<>();
        map.put("lowPrice",lowPrice);
        map.put("highPrice",highPrice);
        Integer result=(Integer) namedParameterJdbcTemplate.query(stringBuilder.toString(),map,productResultSetExtractor);
        return result.toString();
    }

    @Override
    public List<Object> showInSellByMapAndMoney(Integer lowPrice, Integer highPrice, Integer page) {
        final String sql =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 and productPrice between :lowPrice and :highPrice order by productNo desc limit  :Page , 9;";
        Map<String,Object> mapQ = new HashMap<>();
        mapQ.put("lowPrice",lowPrice);
        mapQ.put("highPrice",highPrice);
        return productUtil.pageCalculate(page,sql,mapQ,namedParameterJdbcTemplate,gamePlatformTypeDAO,orderDetailService);
    }

    @Override
    public String showSellProductByKeyWordPages(String keyWord) {
        String appendSql = "and ProductName LIKE :keyWord";
        StringBuilder stringBuilder = new StringBuilder(ShowInSellCount);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(appendSql);
        Map<String,Object> map = new HashMap<>();
        map.put("keyWord","%"+keyWord+"%");
        Integer result=(Integer) namedParameterJdbcTemplate.query(stringBuilder.toString(),map,productResultSetExtractor);
        return result.toString();
    }

    @Override
    public List<Object> showSellProductByKeyWord(String keyWord, Integer page) {
        final String sql =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice FROM product where ProductState = 1 and ProductName LIKE :keyWord order by productNo desc limit  :Page , 9;";
        Map<String,Object> mapQ = new HashMap<>();
        mapQ.put("keyWord","%"+keyWord+"%");
        return productUtil.pageCalculate(page,sql,mapQ,namedParameterJdbcTemplate,gamePlatformTypeDAO,orderDetailService);
    }
}
