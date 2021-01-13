package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.service.ClientService;
import it.unicam.travisbug.c3.service.CourierService;
import it.unicam.travisbug.c3.service.MerchantService;
import it.unicam.travisbug.c3.service.impl.ClientServiceImpl;
import it.unicam.travisbug.c3.service.impl.CourierServiceImpl;
import it.unicam.travisbug.c3.service.impl.MerchantServiceImpl;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
