package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.service.ClientService;
import it.unicam.travisbug.c3.service.impl.ClientServiceImpl;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Registration {

    @PostMapping("/login")
    public String login(Model model) {
        return "";
    }

    @PostMapping("/register")
    public String register(Model model,
                           String name,
                           String surname,
                           String email,
                           String password,
                           String phone) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setPassword(PasswordTool.getMD5String(password));
        client.setEmail(email);
        if (phone != null)
            client.setPhone(phone);
        ClientService service = ClientServiceImpl.getClientServiceInstance();
        service.saveClient(client);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registration";
    }

}
