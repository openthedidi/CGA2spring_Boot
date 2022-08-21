package com.cj.cga101g1.productPic.service;

import com.cj.cga101g1.productPic.dao.ProductPicDao;
import com.cj.cga101g1.productPic.model.ProductPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPicServiceImp implements ProductPicService{
    @Autowired
    private ProductPicDao productPicDao;

    @Override
    public ProductPic getOneCoverByProductNo(Integer productNO) {
        ProductPic productPic = productPicDao.getOneCoverByProductNo(productNO);
        return productPic;
    }

    @Override
    public ProductPic getOneCoverByProductPicNo(Integer ProductPicNo) {
        ProductPic productPic = productPicDao.getOneCoverByProductPicNo(ProductPicNo);
        return productPic;
    }

    @Override
    public List<ProductPic> productAllPics(Integer productNo) {
        return productPicDao.findByPrimaryKeyInBase64(productNo);
    }
}
