package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "ID")
    private Order order;

}
