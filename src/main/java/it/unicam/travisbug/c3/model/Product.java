package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer product_id;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String product_description;

    @Column(nullable = false)
    private Double product_price;

    @Column(nullable = false)
    private Double product_weight;

    @Column(nullable = false)
    private Integer product_supply;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany
    private Set<OrderDetails> orderDetails;

    @OneToMany
    private Set<ProductPromotion> productPromotion;


}
