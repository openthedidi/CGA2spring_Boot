package com.cj.cga101g1.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "product")
@Schema(description = "商品實體類")
public class Product extends ProductErrorMessage implements Serializable {
//    @JsonProperty("productNumber")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "產品編號",example = "21001")
    private Integer productNo;
    @NotNull
    @Schema(description = "遊戲類型編號")
    private Integer gameTypeNo;
    @NotNull
    @Schema(description = "遊戲平台編號")
    private Integer gamePlatformNo;
    @NotNull
    @Schema(description = "遊戲公司編號")
    private Integer gameCompanyNo;
    @NotNull
    @Schema(description = "遊戲名稱")
    private String productName;
    @NotNull
    @Schema(description = "遊戲價格")
    private Integer productPrice;
    @NotNull
    @Schema(description = "商品狀態")
    private Integer productState;
    @Schema(description = "商品狀態種類")
    private ProductStatusCategory productStatusCategory;
    @Schema(description = "賣出時間")
    private Timestamp soldTime;
    @Schema(description = "運送到達時間")
    private Timestamp launchedTime;
    @NotNull
    @Schema(description = "商品描述")
    private String itemProdDescription;
    @NotNull
    @Schema(description = "upcNum碼")
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
