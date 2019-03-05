package com.model;

import java.io.Serializable;

public class Seller implements Serializable{
    private String sellerCompany;
    private String sellerName;
    private String sellerAdress;
    private String sellerPostcode;

    public Seller() {
    }

    private String sellerCity;



    public Seller(String sellerCompany, String sellerName, String sellerAdress, String sellerPostcode, String sellerCity) {
        this.sellerCompany = sellerCompany;
        this.sellerName = sellerName;
        this.sellerAdress = sellerAdress;
        this.sellerPostcode = sellerPostcode;
        this.sellerCity = sellerCity;
    }

    public String getSellerCompany() {
        return sellerCompany;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerAdress() {
        return sellerAdress;
    }

    public String getSellerPostcode() {
        return sellerPostcode;
    }

    public String getSellerCity() {
        return sellerCity;
    }

}
