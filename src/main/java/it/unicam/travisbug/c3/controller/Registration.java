package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.repository.ClientRepository;
import it.unicam.travisbug.c3.service.ClientService;
import it.unicam.travisbug.c3.service.CourierService;
import it.unicam.travisbug.c3.service.MerchantService;
import it.unicam.travisbug.c3.service.impl.ClientServiceImpl;
import it.unicam.travisbug.c3.service.impl.CourierServiceImpl;
import it.unicam.travisbug.c3.service.impl.MerchantServiceImpl;
import it.unicam.travisbug.c3.service.impl.ShopServiceImpl;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class Registration {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private CourierServiceImpl courierService;

    @PostMapping("/login")
    public String login(Model model,
                        String email,
                        String password,
                        @RequestParam(value = "checkboxName", required = false) String remember,
                        HttpServletResponse response) {
        ClientService clientService = ClientServiceImpl.getServiceInstance();
        CourierService courierService = CourierServiceImpl.getServiceInstance();
        MerchantService merchantService = MerchantServiceImpl.getServiceInstance();
        boolean rememberState = false;
        if(remember!=null)
            rememberState = true;
        Client client = clientService.findByEmailAndPass(email, PasswordTool.getMD5String(password));
        if (client != null) {
            setRoleCookie("client", response);
            setUserIDCookie(client.getId(), response, rememberState);
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(Model model,
                           String name,
                           String surname,
                           String email,
                           String password,
                           String phone,
                           String type,
                           HttpServletResponse response) {
        if(type.equals("Client")) {
            registerClient(name, surname, email, password, phone, response);
        }else{
            registerCourier(name, surname, email, password, phone, response);
        }
        return "redirect:/";
    }

    private void registerClient(String name, String surname, String email, String password, String phone, HttpServletResponse response) {
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setName(name);
        client.setSurname(surname);
        client.setPassword(PasswordTool.getMD5String(password));
        client.setEmail(email);
        if (phone != null)
            client.setPhone(phone);
        clientService.saveClient(client);

        setUserIDCookie(client.getId(), response, true);
        setRoleCookie("client", response);
    }

    private void registerCourier(String name, String surname, String email, String password, String phone, HttpServletResponse response) {
        Courier courier = new Courier();
        courier.setId(UUID.randomUUID().toString());
        courier.setName(name);
        courier.setSurname(surname);
        courier.setPassword(PasswordTool.getMD5String(password));
        courier.setEmail(email);
        if (phone != null)
            courier.setPhone(phone);
        courierService.saveCourier(courier);

        setUserIDCookie(courier.getId(), response, true);
        setRoleCookie("courier", response);
    }

    private void setRoleCookie(String role, HttpServletResponse response) {
        Cookie cookie = new Cookie("role", role);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    private void setUserIDCookie(String id, HttpServletResponse response, Boolean remember) {
        Cookie cookie = new Cookie("user_id", id);
        if (remember)
            cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registration";
    }


}
