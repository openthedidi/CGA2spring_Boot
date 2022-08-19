package com.cj.cga101g1.productPic.dao;

import com.cj.cga101g1.productPic.model.ProductPic;

public interface ProductPicDao {
    ProductPic getOneCoverByProductNo(Integer productNO);

    ProductPic getOneCoverByProductPicNo(Integer ProductPicNo);

}
