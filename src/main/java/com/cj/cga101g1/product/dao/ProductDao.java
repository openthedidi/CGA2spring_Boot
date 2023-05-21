package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    Map<String,Object> findByPrimaryKey(Integer ProductNo);

    String showSelledCount();

    List<Object> getPageInSellByMap(Integer page);

    String showSelledCountByPlatFormType(Integer gamePlatformNo);

    List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page);

    String showSellAndGameTypeProductPages(Integer gameTypeNo);

    List<Object> showSellAndGameTypeProduct(Integer gameTypeNo, Integer page);

    String showSellCountByMoney(Integer lowPrice, Integer highPrice);

    List<Object> showInSellByMapAndMoney(Integer lowPrice, Integer highPrice, Integer page);

    String showSellProductByKeyWordPages(String keyWord);

    List<Object> showSellProductByKeyWord(String keyWord, Integer page);

    void createProduct(Product product);

    List showAllProductsName();

    Page<Product> findAll(Pageable pageable);

    Page<Product> showProductByPageAndPageSize(Specification<Product> spec, Pageable pageable);
}
