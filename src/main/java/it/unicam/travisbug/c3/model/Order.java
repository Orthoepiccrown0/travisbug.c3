package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date order_date;

    @Column(nullable = false)
    private String order_status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    private Shipping shipping;

    @OneToMany
    private Set<OrderDetails> orderDetails;

    //TODO: billing address table?
//    private BillingAddress billingAddress;
}
