package com.tricky__tweaks.jugaad.Model;

import java.io.Serializable;

public class EachItemDataModel implements Serializable {

    private String itemName;
    private String itemCategory;
    private long itemMainPrice;
    private long itemRentPrice;
    private long itemDepositPrice;
    private long item_priority;
    private String itemImageDownloadUrl;
    private String itemId;


    public EachItemDataModel() {
    }

    public EachItemDataModel(String itemName, String itemCategory, int itemMainPrice, int itemRentPrice, int itemDepositPrice, String itemImageDownloadUrl, String itemId, int item_priority) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemMainPrice = itemMainPrice;
        this.itemRentPrice = itemRentPrice;
        this.itemDepositPrice = itemDepositPrice;
        this.itemImageDownloadUrl = itemImageDownloadUrl;
        this.itemId = itemId;
        this.item_priority = item_priority;
    }

    public void setItemOrderId(String itemId) {
        this.itemId = itemId;
    }

    public void setItem_priority(long item_priority) {
        this.item_priority = item_priority;
    }

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

    public long getItemMainPrice() {
        return itemMainPrice;
    }

    public void setItemMainPrice(long itemMainPrice) {
        this.itemMainPrice = itemMainPrice;
    }

    public long getItemRentPrice() {
        return itemRentPrice;
    }

    public void setItemRentPrice(long itemRentPrice) {
        this.itemRentPrice = itemRentPrice;
    }

    public long getItemDepositPrice() {
        return itemDepositPrice;
    }

    public void setItemDepositPrice(long itemDepositPrice) {
        this.itemDepositPrice = itemDepositPrice;
    }

    public String getItemImageDownloadUrl() {
        return itemImageDownloadUrl;
    }

    public void setItemImageDownloadUrl(String itemImageDownloadUrl) {
        this.itemImageDownloadUrl = itemImageDownloadUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemOrderId() {
        return itemId;
    }

    public long getItem_priority() {
        return item_priority;
    }
}
