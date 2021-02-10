package it.unicam.travisbug.c3.model;

import org.decimal4j.util.DoubleRounder;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.*;

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

    private Integer discount;

    public boolean promoted;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "ID")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetails> orderDetails;

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getDiscountedPrice() {
        if (this.getDiscount() == null || this.getDiscount() == 0) {
            if (promoted)
                return applyPromotion(price);
            return this.getPrice();
        } else {
            double discountedPrice = (1.0 - (this.getDiscount() / 100.0)) * this.getPrice();
            if (promoted)
                discountedPrice = applyPromotion(discountedPrice);
            return DoubleRounder.round(discountedPrice, 2);
        }
    }

    double applyPromotion(double price) {
        Date today = new Date();
        if (promotion != null && !promotion.isEmpty()) {
            for (Promotion pr : promotion) {
                if (isWithinRange(today, pr.getStart(), pr.getEnd())) {
                    double discount = price - ((1.0 - (pr.getDiscount() / 100.0)) * price);
                    price -= discount;
                } else {
                    promotion.remove(pr);
                }
            }
            if (promotion.isEmpty())
                promoted = false;
        }
        return price;
    }


    boolean isWithinRange(Date today, Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(today);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(startDate);
        int startDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(endDate);
        int endDay = cal.get(Calendar.DAY_OF_MONTH);
        if (day == startDay && day == endDay)
            return true;
        return !(today.before(startDate) || today.after(endDate));
    }

    public void addOrderDetails(OrderDetails order) {
        if (orderDetails == null)
            orderDetails = new HashSet<>();
        orderDetails.add(order);
    }

    public void addPromotion(Promotion p) {
        if (promotion == null)
            promotion = new HashSet<>();
        promotion.add(p);
    }

    public boolean decreaseSupplyBy(int x) {
        if(x<=supply) {
            supply-=x;
            return true;
        }else{
            return false;
        }
    }

    public void addSupply(Integer sup) {
        this.supply = this.supply + sup;
    }
}
