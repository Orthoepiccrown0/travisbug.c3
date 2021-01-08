package it.unicam.travisbug.c3.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Client extends RegisteredUser{
    @OneToMany(mappedBy = "client")
    private Set<Order> order;
}
