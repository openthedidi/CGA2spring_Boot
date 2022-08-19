package com.cj.cga101g1.productPic.util;

import com.cj.cga101g1.productPic.model.ProductPic;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductPicRowMapper implements RowMapper<ProductPic> {
    @Override
    public ProductPic mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductPic productPic = new ProductPic();
        productPic.setProductPicContent(rs.getBytes("ProductPicContent"));
        productPic.setProductPicNO(rs.getInt("ProductPicNO"));
        productPic.setProductNO(rs.getInt("ProductNo"));
        return productPic;
    }
}
