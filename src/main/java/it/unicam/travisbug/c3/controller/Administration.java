package it.unicam.travisbug.c3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Administration {

    @GetMapping("/admin")
    public String admin(Model model) {
        return "Hello, admin";
    }

}
