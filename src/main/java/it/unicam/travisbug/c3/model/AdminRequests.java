package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class AdminRequests {

    @Id
    private String id;

    private String title;

    private String comment;

    @OneToOne
    private Shop shop;

    @OneToOne
    private Promotion promotion;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
