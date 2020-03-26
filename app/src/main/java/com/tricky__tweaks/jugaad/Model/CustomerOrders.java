package com.tricky__tweaks.jugaad.Model;

public class CustomerOrders  {

    private String rentDuration;
    private int quantity;
    private int orderStatus;
    private int itemPriority;
    private String date;
    private String itemId;
    private String customerId;
    private String returnDate;
    private long location_latitude;
    private long location_longitude;
    private String deliveryCoordinates;
    private boolean isPaid;

    public CustomerOrders() {
    }

    public CustomerOrders(String rentDuration, int quantity, int orderStatus, int itemPriority, String date, String itemId, String customerId, String returnDate, String deliveryCoordinates, boolean isPaid) {
        this.rentDuration = rentDuration;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.itemPriority = itemPriority;
        this.date = date;
        this.itemId = itemId;
        this.customerId = customerId;
        this.returnDate = returnDate;
        this.deliveryCoordinates = deliveryCoordinates;
        this.isPaid = isPaid;
    }

    public String  getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(String rentDuration) {
        this.rentDuration = rentDuration;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getItemPriority() {
        return itemPriority;
    }

    public void setItemPriority(int itemPriority) {
        this.itemPriority = itemPriority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDeliveryCoordinates() {
        return deliveryCoordinates;
    }

    public void setDeliveryCoordinates(String deliveryCoordinates) {
        this.deliveryCoordinates = deliveryCoordinates;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
