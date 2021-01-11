package it.unicam.travisbug.c3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String showHome(){
        return "index";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

}