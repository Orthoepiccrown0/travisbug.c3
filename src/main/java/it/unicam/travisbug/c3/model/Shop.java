package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Boolean approved;

    @Column(nullable = false)
    private String shopName;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private Merchant merchant;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "shop_category_id", referencedColumnName = "ID")
    private ShopCategory shopCategory;

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id.equals(shop.id) &&
                shopName.equals(shop.shopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopName);
    }
}
