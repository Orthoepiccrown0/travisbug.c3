package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employee_id;

    @Column(nullable = false)
    private String employee_name;

    @Column(nullable = false)
    private String employee_email;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop employee_shop;

}
