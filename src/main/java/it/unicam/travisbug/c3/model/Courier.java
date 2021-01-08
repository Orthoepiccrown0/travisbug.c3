package it.unicam.travisbug.c3.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Courier extends RegisteredUser {
    @OneToMany(mappedBy = "shipping_courier")
    private Set<Shipping> shipping;
}
