package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Merchant extends RegisteredUser{

    @OneToOne(cascade = CascadeType.ALL)
    private Shop merchant_shop;

    @OneToMany
    private Set<Product> product;
}
