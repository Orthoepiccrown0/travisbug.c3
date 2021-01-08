package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetails> orderDetails;

    @ManyToMany
    @JoinTable(
            name = "product_promotions",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    )
    private Set<Promotion> promotion;


}
