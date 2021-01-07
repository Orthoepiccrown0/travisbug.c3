package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer category_id;

    @Column(nullable = false)
    private String category_name;

    @Column(nullable = false)
    private String category_description;

    @OneToMany
    private Set<Product> product;
}
