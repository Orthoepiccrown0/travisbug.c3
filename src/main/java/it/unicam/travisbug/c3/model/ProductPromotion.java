package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class ProductPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer product_promotion_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

}
