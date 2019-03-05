package com.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String description;
    private int quantity;
    private double netPrice;
    private double taxRate;
    private Seller seller;
    private Buyer buyer;

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }


    public Order(String description, int quantity, double netPrice, double taxRate, Seller seller, Buyer buyer) {
        this.description = description;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.taxRate = taxRate;
        this.seller = seller;
        this.buyer = buyer;
    }





    public Order() {

    }
}
