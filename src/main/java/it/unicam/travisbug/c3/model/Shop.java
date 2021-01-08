package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String shop_shopName;

    @OneToOne(mappedBy = "shop")
    private Merchant merchant;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "shop_category_id", referencedColumnName = "id")
    private ShopCategory shopCategory;

}
