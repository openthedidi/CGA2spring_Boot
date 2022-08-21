package com.cj.cga101g1.productPic.model;


import org.springframework.stereotype.Component;

@Component
public class ProductPic {
    private Integer productPicNO;
    private Integer productNO;
    private byte[] productPicContent;

    private String imageUrl;
    private String productPicContentBase64;

    public Integer getProductPicNO() {
        return productPicNO;
    }

    public void setProductPicNO(Integer productPicNO) {
        this.productPicNO = productPicNO;
    }

    public Integer getProductNO() {
        return productNO;
    }

    public void setProductNO(Integer productNO) {
        this.productNO = productNO;
    }

    public byte[] getProductPicContent() {
        return productPicContent;
    }

    public void setProductPicContent(byte[] productPicContent) {
        this.productPicContent = productPicContent;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductPicContentBase64() {
        return productPicContentBase64;
    }

    public void setProductPicContentBase64(String productPicContentBase64) {
        this.productPicContentBase64 = productPicContentBase64;
    }
}
