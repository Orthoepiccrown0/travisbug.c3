package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double shipping_shipCharge;

    @Column(nullable = false)
    private String shipping_status;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier shipping_courier;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address shipping_address;

    @OneToOne(mappedBy = "shipping")
    private Order shipping_order;

}
