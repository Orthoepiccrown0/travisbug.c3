package it.unicam.travisbug.c3.model;

import javax.persistence.*;

@Entity
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String user_surname;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_password;

    private String user_phone;

}
