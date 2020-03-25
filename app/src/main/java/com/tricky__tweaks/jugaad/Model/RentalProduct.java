package com.tricky__tweaks.jugaad.Model;

public class RentalProduct {
    private String productName;
    private String productPrice;
    private int imageDrawable;

    public RentalProduct() {

    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public RentalProduct(String productName, String productPrice, int imageDrawable) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageDrawable = imageDrawable;
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
