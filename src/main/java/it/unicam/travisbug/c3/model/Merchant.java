package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Merchant extends RegisteredUser{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id", referencedColumnName = "ID")
    private Shop shop;

    @OneToMany(mappedBy = "merchant")
    private Set<Product> product;

}
