package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class Merchant extends RegisteredUser{

    @OneToOne(mappedBy = "id",cascade = CascadeType.ALL)
    private Shop shop;
}
