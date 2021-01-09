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
    private Date order_date;

    @Column(nullable = false)
    private String order_status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    //TODO: billing address table?
//    private BillingAddress billingAddress;
}
