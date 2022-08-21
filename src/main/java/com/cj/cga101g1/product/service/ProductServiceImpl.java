package com.cj.cga101g1.product.service;


import com.cj.cga101g1.product.dao.ProductDao;

import com.cj.cga101g1.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;


    @Override
    public Object findByPrimaryKey(Integer ProductNo) {
        return productDao.findByPrimaryKey(ProductNo);

    }

    @Override
    public String showSelledCount() {
        return productDao.showSelledCount();
    }

    @Override
    public List<Object> getAllSelledProductsByMap(Integer page) {
        return productDao.getPageInSellByMap(page);
    }

    @Override
    public String showSelledCountByPlatFormType(Integer gamePlatformNo) {
        return productDao.showSelledCountByPlatFormType(gamePlatformNo);
    }

    @Override
    public List<Object> showSellAndPlatformProduct(Integer gamePlatformNo,Integer Page) {
        return productDao.showSellAndPlatformProduct(gamePlatformNo,Page);
    }

    @Override
    public String showSellAndGameTypeProductPages(Integer gameTypeNo) {
        return productDao.showSellAndGameTypeProductPages(gameTypeNo);
    }

    @Override
    public List<Object> showSellAndGameTypeProduct(Integer gameTypeNo, Integer page) {
        return productDao.showSellAndGameTypeProduct(gameTypeNo,page);
    }

    @Override
    public String showSellCountByMoney(Integer lowPrice, Integer highPrice) {
        return productDao.showSellCountByMoney(lowPrice,highPrice);
    }

    @Override
    public List<Object> showInSellByMapAndMoney(Integer lowPrice, Integer highPrice, Integer page) {
        return productDao.showInSellByMapAndMoney(lowPrice,highPrice,page);
    }

    @Override
    public String showSellProductByKeyWordPages(String keyWord) {
        return productDao.showSellProductByKeyWordPages(keyWord);
    }

    @Override
    public List<Object> showSellProductByKeyWord(String keyWord, Integer page) {
        return productDao.showSellProductByKeyWord(keyWord,page);
    }
}
