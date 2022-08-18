package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;

public interface ProductDao {

    Product findByPrimaryKey(Integer ProductNo);
    String showSelledCount();

}
