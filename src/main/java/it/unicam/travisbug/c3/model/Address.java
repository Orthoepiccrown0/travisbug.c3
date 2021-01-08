package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String address_city;

    @Column(nullable = false)
    private String address_street;

    @Column(nullable = false)
    private Integer address_number;

    @OneToMany(mappedBy = "shipping_address")
    private Set<Shipping> shipping;

}
