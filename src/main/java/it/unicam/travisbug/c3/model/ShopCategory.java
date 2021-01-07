package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ShopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shop_category_id;

    @Column(nullable = false)
    private String nameCategory;

    @Column(nullable = false)
    private String categoryDescription;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Shop> shop;


}
