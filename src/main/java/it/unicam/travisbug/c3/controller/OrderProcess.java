package it.unicam.travisbug.c3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderProcess {

    @GetMapping("account/orders/process/{id}")
    public String process(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        return "orderProcess";
    }

}
