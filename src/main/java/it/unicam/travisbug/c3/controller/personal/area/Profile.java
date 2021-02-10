package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Profile {

    DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/account")
    public String showAccount(Model model,
                              @CookieValue(value = "user_id", defaultValue = "") String userid,
                              @CookieValue(value = "role", defaultValue = "") String roleCookie) {
        AppCookies appCookies = AppCookies.getInstance();
        appCookies.checkLogged(model, userid, roleCookie, dbManager);
        if (roleCookie != null) {
            Roles role = Roles.valueOf(roleCookie);
            model.addAttribute("role", role);
            setUser(model, userid, role);
        }
        return "accounts/account";
    }

    private void setUser(Model model, String userid, Roles role) {
        Employee employee;
        Courier courier;
        Client client;
        Merchant merchant;
        RegisteredUser user;
        if (role == Roles.CLIENT) {
            client = dbManager.getClientService().findById(userid).orElseThrow();
            user = client;
            model.addAttribute("client", client);
            model.addAttribute("generic", user);
        } else if (role == Roles.COURIER) {
            courier = dbManager.getCourierService().findById(userid).orElseThrow();
            user = courier;
            model.addAttribute("courier", courier);
            model.addAttribute("generic", user);
        } else if (role == Roles.MERCHANT) {
            merchant = dbManager.getMerchantService().findById(userid).orElseThrow();
            user = merchant;
            model.addAttribute("merchant", merchant);
            model.addAttribute("generic", user);
        } else if (role == Roles.EMPLOYEE) {
            employee = dbManager.getEmployeeService().findById(userid).orElseThrow();
            user = employee;
            model.addAttribute("employee", employee);
            model.addAttribute("generic", user);
        }

    }
}
