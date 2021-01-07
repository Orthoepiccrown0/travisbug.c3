package it.unicam.travisbug.c3.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Set;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String promotion_id;

    @Column(nullable = false)
    private Date promotion_start;

    @Column(nullable = false)
    private Date promotion_end;

    @Column(nullable = false)
    private Integer promotion_discount;

    @OneToMany
    private Set<ProductPromotion> productPromotion;

}
