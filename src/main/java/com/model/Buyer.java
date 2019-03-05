package com.model;

import java.io.Serializable;

public class Buyer implements Serializable {
    private String buyerName;
    private String buyerAdress;
    private String buyerPostcode;
    private String buyerCity;

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerAdress(String buyerAdress) {
        this.buyerAdress = buyerAdress;
    }

    public void setBuyerPostcode(String buyerPostcode) {
        this.buyerPostcode = buyerPostcode;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }



    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerAdress() {
        return buyerAdress;
    }

    public String getBuyerPostcode() {
        return buyerPostcode;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public Buyer() {
    }

    public Buyer(String buyerName, String buyerAdress, String buyerPostcode, String buyerCity) {

        this.buyerName = buyerName;
        this.buyerAdress = buyerAdress;
        this.buyerPostcode = buyerPostcode;
        this.buyerCity = buyerCity;
    }
}
