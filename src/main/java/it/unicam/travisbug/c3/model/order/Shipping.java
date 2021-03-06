package it.unicam.travisbug.c3.model.order;

import it.unicam.travisbug.c3.model.users.Courier;
import it.unicam.travisbug.c3.utils.ShippingStatus;

import javax.persistence.*;

@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private ShippingStatus shippingStatus;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "ID")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "ID")
    private Address address;

    @OneToOne(mappedBy = "shipping")
    private Order shipping_order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order getShipping_order() {
        return shipping_order;
    }

    public void setShipping_order(Order shipping_order) {
        this.shipping_order = shipping_order;
    }
}
