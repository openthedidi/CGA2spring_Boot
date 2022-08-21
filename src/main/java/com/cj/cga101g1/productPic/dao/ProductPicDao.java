package com.cj.cga101g1.productPic.dao;

import com.cj.cga101g1.productPic.model.ProductPic;

import java.util.List;

public interface ProductPicDao {
    ProductPic getOneCoverByProductNo(Integer productNO);

    ProductPic getOneCoverByProductPicNo(Integer ProductPicNo);
    List<ProductPic> findByPrimaryKeyInBase64(Integer productNo);

}
