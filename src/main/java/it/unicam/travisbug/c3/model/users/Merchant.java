package it.unicam.travisbug.c3.model.users;

import it.unicam.travisbug.c3.model.shop.Product;
import it.unicam.travisbug.c3.model.shop.Shop;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Merchant extends RegisteredUser {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id", referencedColumnName = "ID")
    private Shop shop;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.REMOVE)
    private Set<Product> product;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

}
