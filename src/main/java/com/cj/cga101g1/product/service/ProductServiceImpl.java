package com.cj.cga101g1.product.service;


import com.cj.cga101g1.product.dao.ProductDao;

import com.cj.cga101g1.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;


    @Override
    public Product findByPrimaryKey(Integer ProductNo) {
        return productDao.findByPrimaryKey(ProductNo);

    }
}
