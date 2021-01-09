package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double shipCharge;

    @Column(nullable = false)
    private String shipStatus;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "ID")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "ID")
    private Address address;

    @OneToOne(mappedBy = "shipping")
    private Order shipping_order;

}
