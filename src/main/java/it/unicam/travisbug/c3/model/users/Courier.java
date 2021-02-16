package it.unicam.travisbug.c3.model.users;

import it.unicam.travisbug.c3.model.order.Shipping;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Courier extends RegisteredUser {

    @OneToMany(mappedBy = "courier")
    private Set<Shipping> shipping;

    public Set<Shipping> getShipping() {
        return shipping;
    }

    public void setShipping(Set<Shipping> shipping) {
        this.shipping = shipping;
    }

}
