package com.cj.cga101g1.product.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
       Product product = new Product();
       //沒有放時間
        product.setProductNo(rs.getInt("ProductNo"));
        product.setGameTypeNo(rs.getInt("GameTypeNo"));
        product.setGamePlatformNo(rs.getInt("GamePlatformNo"));
        product.setGameCompanyNo(rs.getInt("GameCompanyNo"));
        product.setProductName(rs.getString("ProductName"));
        product.setProductPrice(rs.getInt("ProductPrice"));

//        Integer productStatus = rs.getInt("ProductState");
//        System.out.println(productStatus);
//        ProductStatusCategory x = ProductStatusCategory.valueOf(productStatus.toString());
//        System.out.println(x);
//        product.setProductStatusCategory(ProductStatusCategory.valueOf(productStatus.toString()));

        product.setProductState(rs.getInt("ProductState"));
        product.setItemProdDescription(rs.getString("ItemProdDescription"));
        product.setUpcNum(rs.getString("UpcNum"));

        return product;
    }
}
