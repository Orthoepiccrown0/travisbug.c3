package it.unicam.travisbug.c3.model.order;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column
    private String street;

    @Column
    private Integer number;

    private Double shipCharge;

    @OneToMany(mappedBy = "address")
    private Set<Shipping> shipping;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getShipCharge() {
        return shipCharge;
    }

    public void setShipCharge(Double shipCharge) {
        this.shipCharge = shipCharge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<Shipping> getShipping() {
        return shipping;
    }

    public void setShipping(Set<Shipping> shipping) {
        this.shipping = shipping;
    }

    public String getShippingChargeString() {
        return String.format("%.2f", shipCharge);
    }

    @Override
    public String toString() {
        if(street==null) {
            return String.format("%s", city);
        }else{
            return String.format("%s, %s %d", city, street, number);
        }
    }
}
