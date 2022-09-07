package com.cj.cga101g1.product.model;

public enum ProductStatusCategory {
    SELL(1),NON_SELL(2);
    private int value;
    ProductStatusCategory(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
