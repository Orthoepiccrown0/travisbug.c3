package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer address_id;

    @Column(nullable = false)
    private String address_city;

    @Column(nullable = false)
    private String address_street;

    @Column(nullable = false)
    private Integer address_number;

    @ManyToOne
    @JoinColumn(name = "shippind_id", nullable = false)
    private Shipping shipping;

}
