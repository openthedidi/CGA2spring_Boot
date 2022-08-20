package com.cj.cga101g1.product.service;

import com.cj.cga101g1.product.model.Product;

import java.util.List;

public interface ProductService {
    Product findByPrimaryKey(Integer ProductNo);

    /**** show出沒有經過篩選的上架商品的數量 ****/
    String showSelledCount();

    /**** show出沒有經過篩選的上架商品的資訊 ****/
    List<Object> getAllSelledProductsByMap(Integer page);

    /**** show出特定遊戲平台的上架商品的數量 ****/
    String showSelledCountByPlatFormType(Integer gamePlatformNo);
    /**** show出特定遊戲平台的上架商品的資訊 ****/
    List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page);
}
