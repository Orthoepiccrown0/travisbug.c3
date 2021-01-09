package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "ID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "ID")
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    //TODO: billing address table?
//    private BillingAddress billingAddress;

}
