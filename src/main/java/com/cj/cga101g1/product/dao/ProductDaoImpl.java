package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.gamecompany.dao.GameCompanyDao;
import com.cj.cga101g1.gameplatformtype.dao.GamePlatformTypeDAO;
import com.cj.cga101g1.gametype.dao.GameTypeDAO;
import com.cj.cga101g1.orderdetail.service.OrderDetailService;
import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.model.ProductRepository;
import com.cj.cga101g1.product.model.ProductResultSetExtractor;
import com.cj.cga101g1.product.model.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao{

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductResultSetExtractor productResultSetExtractor;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private  GamePlatformTypeDAO gamePlatformTypeDAO;
    @Autowired
    private GameTypeDAO gameTypeDAO;
    @Autowired
    private GameCompanyDao gameCompanyDao;

    @Autowired
    private  ProductUtil productUtil;

    final private String ShowInSellCount =
            "SELECT count(productNo) FROM product where ProductState = 1 ;";

    @Override
    public Map<String,Object> findByPrimaryKey(Integer productNo) {
        final String GET_ONE =
                "SELECT productNo,gameTypeNo,gamePlatformNo,gameCompanyNo," +
                        "productName,productPrice,productState,itemProdDescription,upcNum " +
                        "FROM product where productNo = :productNo";

        Map<String,Object> map = new HashMap<>();
        map.put("productNo",productNo);
        List<Product>list = namedParameterJdbcTemplate.query(GET_ONE,map,new ProductRowMapper());
        Product productVO =list.get(0);
        map.put("productName", productVO.getProductName());
        map.put("productNo", productVO.getProductNo().toString());
        map.put("gameTypeName", gameTypeDAO.getGameType(productVO.getGameTypeNo()).getGameTypeName());
        map.put("gamePlatformName", gamePlatformTypeDAO.getType(productVO.getGamePlatformNo()).getGamePlatformName());
        map.put("gameCompanyName", gameCompanyDao.findByNo(productVO.getGameCompanyNo()).getGameCompanyName());
        map.put("itemProdDescription", productVO.getItemProdDescription());
        map.put("productPrice", productVO.getProductPrice().toString());
        return map;
    }

    @Override
    public String showSelledCount() {
        Integer result=(Integer) namedParameterJdbcTemplate.query(ShowInSellCount,productResultSetExtractor);
        return result.toString();
    }

    @Override
    public List<Object> getPageInSellByMap(Integer page) {
        final String sql = "SELECT a.productNo,a.gameTypeNo,a.gamePlatformNo,a.gameCompanyNo,a.productName,a.productPrice,b.GamePlatformName FROM product a  "
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

    @Transactional
    @Override
    public void createProduct(Product productVO) {
        System.out.println("createProduct");
        final String sql =
                "insert into product(gameTypeNo,gamePlatformNo,gameCompanyNo,productName,productPrice,productState,itemProdDescription,upcNum) VALUES (:gameTypeNo,:gamePlatformNo,:gameCompanyNo,:productName,:productPrice,:productState,:itemProdDescription,:upcNum);";
        Map<String,Object> map = new HashMap<>();
        map.put("productName", productVO.getProductName());
        map.put("gameTypeNo",  productVO.getGameTypeNo());
        map.put("gamePlatformNo", productVO.getGamePlatformNo());
        map.put("gameCompanyNo", productVO.getGameCompanyNo());
        map.put("itemProdDescription", productVO.getItemProdDescription());
        map.put("productPrice", productVO.getProductPrice().toString());
        map.put("productState", productVO.getProductState());
        map.put("upcNum", productVO.getUpcNum());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List showAllProductsName() {
        List<Product> list = new ArrayList<>();
        List<String> result = productRepository.showAllProductsName();
        for (String string: result) {
            Product product = new Product();
            Integer productNo = Integer.valueOf(string.split(",")[0]);
            String productName = string.split(",")[1];
            product.setProductNo(productNo);
            product.setProductName(productName);
            list.add(product);
        }
        return list;
    }
}
