package com.cj.cga101g1.productPic.service;


import com.cj.cga101g1.productPic.model.ProductPic;

public interface ProductPicService {
    public ProductPic getOneCoverByProductNo(Integer productNO);
    ProductPic getOneCoverByProductPicNo(Integer ProductPicNo);
}
