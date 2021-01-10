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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getSupply() {
        return supply;
    }

    public void setSupply(Integer supply) {
        this.supply = supply;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(Set<Promotion> promotion) {
        this.promotion = promotion;
    }
}
