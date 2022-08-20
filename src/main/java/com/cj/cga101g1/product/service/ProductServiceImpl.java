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
    public Product findByPrimaryKey(Integer ProductNo) {
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
}
