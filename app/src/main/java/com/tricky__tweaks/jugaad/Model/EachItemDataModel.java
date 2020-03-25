package com.tricky__tweaks.jugaad.Model;

import java.io.Serializable;

public class EachItemDataModel implements Serializable {

    private String itemName;
    private String itemCategory;
    private String itemMainPrice;
    private String itemRentPrice;
    private String itemDepositPrice;
    private String itemImageDownloadUrl;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemMainPrice() {
        return itemMainPrice;
    }

    public void setItemMainPrice(String itemMainPrice) {
        this.itemMainPrice = itemMainPrice;
    }

    public String getItemRentPrice() {
        return itemRentPrice;
    }

    public void setItemRentPrice(String itemRentPrice) {
        this.itemRentPrice = itemRentPrice;
    }

    public String getItemDepositPrice() {
        return itemDepositPrice;
    }

    public void setItemDepositPrice(String itemDepositPrice) {
        this.itemDepositPrice = itemDepositPrice;
    }

    public String getItemImageDownloadUrl() {
        return itemImageDownloadUrl;
    }

    public void setItemImageDownloadUrl(String itemImageDownloadUrl) {
        this.itemImageDownloadUrl = itemImageDownloadUrl;
    }

    public EachItemDataModel(String itemName, String itemCategory, String itemMainPrice, String itemRentPrice, String itemDepositPrice, String itemImageDownloadUrl) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemMainPrice = itemMainPrice;
        this.itemRentPrice = itemRentPrice;
        this.itemDepositPrice = itemDepositPrice;
        this.itemImageDownloadUrl = itemImageDownloadUrl;
    }

    public EachItemDataModel() {}

}
