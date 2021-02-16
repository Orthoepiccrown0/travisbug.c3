package it.unicam.travisbug.c3.model.requests;

import it.unicam.travisbug.c3.model.shop.Shop;
import it.unicam.travisbug.c3.model.users.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmployeeRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date date;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "ID")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "ID")
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
