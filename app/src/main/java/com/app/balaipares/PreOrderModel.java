package com.app.balaipares;

public class PreOrderModel {
    String order,addOns,quantity,payTotal;

    public PreOrderModel(){

    }

    public PreOrderModel(String order, String addOns, String quantity,String payTotal) {
        this.order = order;
        this.addOns = addOns;
        this.quantity = quantity;
        this.payTotal = payTotal;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAddOns() {
        return addOns;
    }

    public void setAddOns(String addOns) {
        this.addOns = addOns;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }
}
