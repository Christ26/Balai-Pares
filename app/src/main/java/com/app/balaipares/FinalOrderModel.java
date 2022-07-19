package com.app.balaipares;

public class FinalOrderModel {
    String foodOrders, addOns,drinksOrders,foodQuantity,drinksQuantity,totalPayment,NameOfReceiver,mobileNumber,address,key;

    public FinalOrderModel(){

    }
    public FinalOrderModel(String foodOrders, String addOns, String drinksOrders, String foodQuantity, String drinksQuantity, String totalPayment, String nameOfReceiver, String mobileNumber, String address,String key) {
        this.foodOrders = foodOrders;
        this.addOns = addOns;
        this.drinksOrders = drinksOrders;
        this.foodQuantity = foodQuantity;
        this.drinksQuantity = drinksQuantity;
        this.totalPayment = totalPayment;
        this.NameOfReceiver = nameOfReceiver;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.key = key;
    }

    public String getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(String foodOrders) {
        this.foodOrders = foodOrders;
    }

    public String getAddOns() {
        return addOns;
    }

    public void setAddOns(String addOns) {
        this.addOns = addOns;
    }

    public String getDrinksOrders() {
        return drinksOrders;
    }

    public void setDrinksOrders(String drinksOrders) {
        this.drinksOrders = drinksOrders;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getDrinksQuantity() {
        return drinksQuantity;
    }

    public void setDrinksQuantity(String drinksQuantity) {
        this.drinksQuantity = drinksQuantity;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getNameOfReceiver() {
        return NameOfReceiver;
    }

    public void setNameOfReceiver(String nameOfReceiver) {
        NameOfReceiver = nameOfReceiver;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
