package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_details_id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
