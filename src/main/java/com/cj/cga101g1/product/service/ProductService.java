package com.cj.cga101g1.product.service;

import com.cj.cga101g1.product.model.Product;

public interface ProductService {
    Product findByPrimaryKey(Integer ProductNo);
    String showSelledCount();
}
