package it.unicam.travisbug.c3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Shop {

    @GetMapping("/shop/{id}")
    public String showShop(Model model, @PathVariable Integer id){
        return "shop";
    }
}
