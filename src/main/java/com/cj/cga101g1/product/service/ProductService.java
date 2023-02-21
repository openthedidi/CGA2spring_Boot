package com.cj.cga101g1.product.service;

import com.cj.cga101g1.product.model.Product;

import java.util.List;

public interface ProductService {
    /*******  查詢單一商品資訊  *******/
    Object findByPrimaryKey(Integer ProductNo);

    /**** show出沒有經過篩選的上架商品的數量 ****/
    String showSelledCount();

    /**** show出沒有經過篩選的上架商品的資訊 ****/
    List<Object> getAllSelledProductsByMap(Integer page);

    /**** show出特定遊戲平台的上架商品的數量 ****/
    String showSelledCountByPlatFormType(Integer gamePlatformNo);
    /**** show出特定遊戲平台的上架商品的資訊 ****/
    List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page);

    /**** show出特定遊戲種類的上架商品的數量 ****/
    String showSellAndGameTypeProductPages(Integer gameTypeNo);
    /**** show出特定遊戲種類的上架商品的資訊 ****/
    List<Object> showSellAndGameTypeProduct(Integer gameTypeNo, Integer page);

    /**** show出特定金額範圍的上架商品的數量 ****/
    String showSellCountByMoney(Integer lowPrice, Integer highPrice);
    /**** show出特定金額範圍的上架商品的資訊 ****/
    List<Object> showInSellByMapAndMoney(Integer lowPrice, Integer highPrice, Integer page);

    /**** show出符合關鍵字的上架商品的數量 ****/
    String showSellProductByKeyWordPages(String keyWord);
    /**** show出符合關鍵字的上架商品的資訊 ****/
    List<Object> showSellProductByKeyWord(String keyWord, Integer page);

    /**** 新增商品 ****/
    Product createProduct(Product product);
}
