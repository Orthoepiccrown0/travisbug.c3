package it.unicam.travisbug.c3.model.order;

import it.unicam.travisbug.c3.model.shop.Shop;
import it.unicam.travisbug.c3.model.users.Client;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_order")
public class Order {

    @Id
    private String id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", referencedColumnName = "ID")
    private Client client;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shipping_id", referencedColumnName = "ID")
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderDetails> orderDetails;

    @ManyToMany
    private List<Shop> shops;

    private boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addShop(Shop shop) {
        if (shops == null)
            shops = new ArrayList<>();
        shops.add(shop);
    }

    public void removeShop(Shop shop) {
        if (shops != null)
            shops.remove(shop);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetails(OrderDetails order) {
        if (orderDetails == null)
            orderDetails = new ArrayList<>();
        orderDetails.add(order);
    }

    public void updateAmount() {
        if (orderDetails != null) {
            amount = 0.0;
            for (OrderDetails order : orderDetails) {
                double price = order.getProduct().getPrice() * order.getQuantity();
                amount += price;
            }
            if (getShipping().getAddress() != null) {
                amount += getShipping().getAddress().getShipCharge();
            }

        }
    }

    public void removeItem(OrderDetails item) {
        orderDetails.remove(item);
    }

    public void changeItem(OrderDetails item) {
        orderDetails.remove(item);
        orderDetails.add(item);
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (OrderDetails order : orderDetails) {
            totalWeight += order.getProduct().getWeight() * order.getQuantity();
        }
        return totalWeight;
    }
}
