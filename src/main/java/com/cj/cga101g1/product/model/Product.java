package com.cj.cga101g1.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
//    @JsonProperty("productNumber")
    private Integer productNo;
    private Integer gameTypeNo;
    private Integer gamePlatformNo;
    private Integer gameCompanyNo;
    private String productName;
    private Integer productPrice;
    private Integer productState;
    private ProductStatusCategory productStatusCategory;
    private Timestamp soldTime;
    private Timestamp launchedTime;
    private String itemProdDescription;
    private String upcNum;

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public Integer getGameTypeNo() {
        return gameTypeNo;
    }

    public void setGameTypeNo(Integer gameTypeNo) {
        this.gameTypeNo = gameTypeNo;
    }

    public Integer getGamePlatformNo() {
        return gamePlatformNo;
    }

    public void setGamePlatformNo(Integer gamePlatformNo) {
        this.gamePlatformNo = gamePlatformNo;
    }

    public Integer getGameCompanyNo() {
        return gameCompanyNo;
    }

    public void setGameCompanyNo(Integer gameCompanyNo) {
        this.gameCompanyNo = gameCompanyNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Timestamp getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(Timestamp soldTime) {
        this.soldTime = soldTime;
    }

    public Timestamp getLaunchedTime() {
        return launchedTime;
    }

    public void setLaunchedTime(Timestamp launchedTime) {
        this.launchedTime = launchedTime;
    }

    public String getItemProdDescription() {
        return itemProdDescription;
    }

    public void setItemProdDescription(String itemProdDescription) {
        this.itemProdDescription = itemProdDescription;
    }

    public String getUpcNum() {
        return upcNum;
    }

    public void setUpcNum(String upcNum) {
        this.upcNum = upcNum;
    }

    public ProductStatusCategory getProductStatusCategory() {
        return productStatusCategory;
    }

    public void setProductStatusCategory(ProductStatusCategory productStatusCategory) {
        this.productStatusCategory = productStatusCategory;
    }
}
