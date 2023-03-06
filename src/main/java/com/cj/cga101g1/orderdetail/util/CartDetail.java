package com.cj.cga101g1.orderdetail.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
public class CartDetail {
    private Integer orderNo;
    private String productName;
    private String productNo;
    private Integer productSales;
    private Integer productTotalPrice;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetail that = (CartDetail) o;
        return  Objects.equals(productNo, that.productNo) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash( productNo);
    }

    public CartDetail getCartDetail(String productName, String productNo, Integer productSales, Integer productTotalPrice){
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProductTotalPrice(productTotalPrice);
        cartDetail.setProductName(productName);
        cartDetail.setProductSales(productSales);
        cartDetail.setProductNo(productNo);
        return cartDetail;
    }
}
