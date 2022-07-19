package com.app.balaipares;

public class SalesModel {
    String totalSales,totalOrder;
    public SalesModel() {
    }

    public SalesModel(String totalSales, String totalOrder) {
        this.totalSales = totalSales;
        this.totalOrder = totalOrder;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }
}
