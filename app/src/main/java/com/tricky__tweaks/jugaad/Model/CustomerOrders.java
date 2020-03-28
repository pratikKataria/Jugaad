package com.tricky__tweaks.jugaad.Model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class CustomerOrders  {

    private String itemId;
    private String itemCategory;
    private String customerId;
    private int quantity;
    private String rentDuration;
    private int orderStatus;
    private String orderedDate;
    private String returnDate;
    private String deliveryDate;
    private long location_latitude;
    private long location_longitude;
    private String deliveryCoordinates;
    private String trackingNumber;
    private long depositAmount;
    private boolean isPaid;
    private int orderNumber;
    private String billingAddress;

    public CustomerOrders() {}

    public CustomerOrders(String itemId, String itemCategory, String customerId, int quantity, String rentDuration, int orderStatus, String orderedDate, String returnDate, String deliveryDate, long location_latitude, long location_longitude, String deliveryCoordinates, String trackingNumber, long depositAmount, boolean isPaid, int orderNumber, String billingAddress) {
        this.itemId = itemId;
        this.itemCategory = itemCategory;
        this.customerId = customerId;
        this.quantity = quantity;
        this.rentDuration = rentDuration;
        this.orderStatus = orderStatus;
        this.orderedDate = orderedDate;
        this.returnDate = returnDate;
        this.deliveryDate = deliveryDate;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.deliveryCoordinates = deliveryCoordinates;
        this.trackingNumber = trackingNumber;
        this.depositAmount = depositAmount;
        this.isPaid = isPaid;
        this.orderNumber = orderNumber;
        this.billingAddress = billingAddress;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(String rentDuration) {
        this.rentDuration = rentDuration;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(long location_latitude) {
        this.location_latitude = location_latitude;
    }

    public long getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(long location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getDeliveryCoordinates() {
        return deliveryCoordinates;
    }

    public void setDeliveryCoordinates(String deliveryCoordinates) {
        this.deliveryCoordinates = deliveryCoordinates;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @NotNull
    @Override
    public String toString() {
        return "CustomerOrders{" +
                "itemId='" + itemId + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", customerId='" + customerId + '\'' +
                ", quantity=" + quantity +
                ", rentDuration='" + rentDuration + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderedDate='" + orderedDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", location_latitude=" + location_latitude +
                ", location_longitude=" + location_longitude +
                ", deliveryCoordinates='" + deliveryCoordinates + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", depositAmount=" + depositAmount +
                ", isPaid=" + isPaid +
                '}';
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
