package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer registered_user_id;

    @Column(nullable = false)
    private String registered_user_name;

    @Column(nullable = false)
    private String registered_user_surname;

    @Column(nullable = false)
    private String registered_user_email;

    @Column(nullable = false)
    private String registered_user_password;

    private String registered_user_phone;

}
