package com.tricky__tweaks.jugaad.Model;

import java.io.Serializable;

public class EachItemDataModel implements Serializable {

    private String itemName;
    private String itemCategory;
    private int itemMainPrice;
    private int itemRentPrice;
    private int itemDepositPrice;
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

    public int getItemMainPrice() {
        return itemMainPrice;
    }

    public void setItemMainPrice(int itemMainPrice) {
        this.itemMainPrice = itemMainPrice;
    }

    public int getItemRentPrice() {
        return itemRentPrice;
    }

    public void setItemRentPrice(int itemRentPrice) {
        this.itemRentPrice = itemRentPrice;
    }

    public int getItemDepositPrice() {
        return itemDepositPrice;
    }

    public void setItemDepositPrice(int itemDepositPrice) {
        this.itemDepositPrice = itemDepositPrice;
    }

    public String getItemImageDownloadUrl() {
        return itemImageDownloadUrl;
    }

    public void setItemImageDownloadUrl(String itemImageDownloadUrl) {
        this.itemImageDownloadUrl = itemImageDownloadUrl;
    }


    public EachItemDataModel(String itemName, String itemCategory, int itemMainPrice, int itemRentPrice, int itemDepositPrice, String itemImageDownloadUrl) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemMainPrice = itemMainPrice;
        this.itemRentPrice = itemRentPrice;
        this.itemDepositPrice = itemDepositPrice;
        this.itemImageDownloadUrl = itemImageDownloadUrl;
    }

    public EachItemDataModel() {}

}
