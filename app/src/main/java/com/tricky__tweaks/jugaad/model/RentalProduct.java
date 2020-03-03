package com.tricky__tweaks.jugaad.model;

public class RentalProduct {
    private String productName;
    private String productPrice;

    public RentalProduct() {

    }

    public RentalProduct(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
