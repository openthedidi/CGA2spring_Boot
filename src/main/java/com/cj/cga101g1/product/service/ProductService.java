package com.cj.cga101g1.product.service;

import com.cj.cga101g1.product.model.Product;

import java.util.List;

public interface ProductService {
    Product findByPrimaryKey(Integer ProductNo);
    String showSelledCount();
    List<Object> getAllSelledProductsByMap(Integer page);
}
