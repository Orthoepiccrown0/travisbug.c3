package it.unicam.travisbug.c3.model.order;

import it.unicam.travisbug.c3.model.shop.Product;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    private String id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "ID")
    private Order order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
