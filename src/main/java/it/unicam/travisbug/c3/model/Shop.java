package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String shopName;

    @OneToOne(mappedBy = "shop")
    private Merchant merchant;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL) //si puo fare 2 volte mappedBy ="shop" ??
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "shop_category_id", referencedColumnName = "ID")
    private ShopCategory shopCategory;

}
