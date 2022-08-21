package com.cj.cga101g1.productPic.service;


import com.cj.cga101g1.productPic.model.ProductPic;

import java.util.List;

public interface ProductPicService {
    ProductPic getOneCoverByProductNo(Integer productNO);
    ProductPic getOneCoverByProductPicNo(Integer ProductPicNo);

    List<ProductPic> productAllPics(Integer productNo);
}
