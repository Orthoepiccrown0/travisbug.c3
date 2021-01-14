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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Home {

    @GetMapping("/")
    public String showHome(Model model,
                           @CookieValue(value = "user_id", defaultValue = "") String userid,
                           @CookieValue(value = "role", defaultValue = "") String role) {
        String logged = "guest";
        if(!userid.equals("") && !role.equals("")){
            logged = "logged";
        }
        model.addAttribute("logged",logged);
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie user_cookie = new Cookie("user_id", "");
        Cookie role_cookie = new Cookie("role", "");
        user_cookie.setMaxAge(0);
        role_cookie.setMaxAge(0);
        response.addCookie(user_cookie);
        response.addCookie(role_cookie);
        return "redirect:/";
    }

    @GetMapping("/adminLogin")
    public String showAdminLogin(){
        return "adminLogin";
    }
}
