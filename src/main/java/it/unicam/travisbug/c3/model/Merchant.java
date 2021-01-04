package it.unicam.travisbug.c3.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Merchant extends RegisteredUser{

    @Column(nullable = false)
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
