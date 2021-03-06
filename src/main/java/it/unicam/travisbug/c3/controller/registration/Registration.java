package it.unicam.travisbug.c3.controller.registration;

import it.unicam.travisbug.c3.model.users.Client;
import it.unicam.travisbug.c3.model.users.Courier;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import it.unicam.travisbug.c3.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class Registration {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    private final AppCookies appCookies = AppCookies.getInstance();

    @PostMapping("/register")
    public String register(Model model,
                           String name,
                           String surname,
                           String email,
                           String password,
                           String phone,
                           String type,
                           HttpServletResponse response,
                           RedirectAttributes redirectAttrs) {
        if (!appCookies.isUsedEmail(email, dbManager)) {
            if (type.equals("Client")) {
                registerClient(name, surname, email, password, phone, response);
            } else {
                registerCourier(name, surname, email, password, phone, response);
            }
            return "redirect:/";
        }
        redirectAttrs.addAttribute("used_email", "used");
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String registerUser(Model model, String used_email) {
        model.addAttribute("used_email", used_email);
        return "accounts/registration";
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
        dbManager.getClientService().saveClient(client);

        appCookies.setUserIDCookie(client.getId(), response);
        appCookies.setRoleCookie(Roles.CLIENT, response);
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
        dbManager.getCourierService().saveCourier(courier);

        appCookies.setUserIDCookie(courier.getId(), response);
        appCookies.setRoleCookie(Roles.COURIER, response);
    }

}
