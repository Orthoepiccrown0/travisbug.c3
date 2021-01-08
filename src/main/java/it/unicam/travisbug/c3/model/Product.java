package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Integer supply;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "ID")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetails> orderDetails;

    @ManyToMany
    @JoinTable(
            name = "product_promotions",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "ID")
    )
    private Set<Promotion> promotion;


}
