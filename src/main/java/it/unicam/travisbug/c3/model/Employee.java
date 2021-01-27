package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class Employee extends RegisteredUser{

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "ID")
    private Shop shop;

    @OneToOne(mappedBy = "employee")
    private EmployeeRequests employeeRequests;

    public EmployeeRequests getEmployeeRequests() {
        return employeeRequests;
    }

    public void setEmployeeRequests(EmployeeRequests employeeRequests) {
        this.employeeRequests = employeeRequests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
