package com.cj.cga101g1.product.service;


import com.cj.cga101g1.product.dao.ProductDao;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.productPic.dao.ProductPicDao;
import com.cj.cga101g1.productPic.service.ProductPicService;
import com.cj.cga101g1.util.specUtil.SpecUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("ProductServiceImp")
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductPicDao productPicDao;


    @Override
    public Object findByPrimaryKey(Integer ProductNo) {
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
    public List<Object> showSellAndPlatformProduct(Integer gamePlatformNo, Integer Page) {
        return productDao.showSellAndPlatformProduct(gamePlatformNo, Page);
    }

    @Override
    public String showSellAndGameTypeProductPages(Integer gameTypeNo) {
        return productDao.showSellAndGameTypeProductPages(gameTypeNo);
    }

    @Override
    public List<Object> showSellAndGameTypeProduct(Integer gameTypeNo, Integer page) {
        return productDao.showSellAndGameTypeProduct(gameTypeNo, page);
    }

    @Override
    public String showSellCountByMoney(Integer lowPrice, Integer highPrice) {
        return productDao.showSellCountByMoney(lowPrice, highPrice);
    }

    @Override
    public List<Object> showInSellByMapAndMoney(Integer lowPrice, Integer highPrice, Integer page) {
        return productDao.showInSellByMapAndMoney(lowPrice, highPrice, page);
    }

    @Override
    public String showSellProductByKeyWordPages(String keyWord) {
        return productDao.showSellProductByKeyWordPages(keyWord);
    }

    @Override
    public List<Object> showSellProductByKeyWord(String keyWord, Integer page) {
        return productDao.showSellProductByKeyWord(keyWord, page);
    }

    @Override
    public Product createProduct(Product product) {
        productDao.createProduct(product);
        return product;
    }

    @Override
    public List showAllProductsName() {
        return productDao.showAllProductsName();
    }


    @Override
    public Page<Product> showProductByPageAndPageSize(Integer page, Integer pageSize, String gamePlatformNo, String gameTypeNo, String lowPrice, String highPrice, String keyWord) {
        Sort sort = Sort.by("productNo").descending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Specification<Product> spec = Specification.where(null);

        if (StringUtils.isNumeric(gamePlatformNo))
            spec = spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("gamePlatformNo"), gamePlatformNo)));
        if (StringUtils.isNumeric(gameTypeNo))
            spec = spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("gameTypeNo"), gameTypeNo)));

        Integer lowPriceInt = StringUtils.isNumeric(lowPrice) ? Integer.valueOf(lowPrice) : 1;
        Integer highPriceInt = StringUtils.isNumeric(highPrice) ? Integer.valueOf(highPrice) : 9999;

        spec = spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("productPrice"), lowPriceInt, highPriceInt)));
        if(StringUtils.isNotEmpty(keyWord)){
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), "%" + keyWord + "%" ));
        }

        Page<Product> productInPage = productDao.showProductByPageAndPageSize(spec, pageable);
        return productInPage;
    }
}
