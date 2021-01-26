package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ShopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nameCategory;

    @Column
    private String categoryDescription;

    @OneToMany(mappedBy = "shopCategory", cascade = CascadeType.ALL)
    private Set<Shop> shop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Set<Shop> getShop() {
        return shop;
    }

    public void setShop(Set<Shop> shop) {
        this.shop = shop;
    }

}
