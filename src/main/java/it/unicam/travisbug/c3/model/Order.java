package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "ID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "ID")
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    //TODO: billing address table?
//    private BillingAddress billingAddress;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
