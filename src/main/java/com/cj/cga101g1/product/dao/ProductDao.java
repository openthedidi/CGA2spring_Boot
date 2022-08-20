package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;

import java.util.List;

public interface ProductDao {

    Product findByPrimaryKey(Integer ProductNo);
    String showSelledCount();
    List<Object> getPageInSellByMap(Integer page);

    String showSelledCountByPlatFormType(Integer gamePlatformNo);

    List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page);
}
