package it.unicam.travisbug.c3.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Client extends RegisteredUser{

    @OneToMany(mappedBy = "client")
    private Set<Order> order;

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }
}
