package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer shop_id;

    @Column(nullable = false)
    private String shop_shopName;

    @OneToOne
    private Merchant merchant;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "shop_category_id", nullable = false)
    private ShopCategory shopCategory;

}
