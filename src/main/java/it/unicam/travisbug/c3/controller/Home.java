package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.AppCommandLine;
import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import it.unicam.travisbug.c3.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Home {

    private DBManager dbManager;

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/")
    public String showHome(Model model,
                           @CookieValue(value = "user_id", defaultValue = "") String userid,
                           @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role);
        List<Shop> shops = dbManager.getShopService().getAll();
        if (shops.size() != 0) {
            shops.removeIf(shop -> !shop.isApproved());
            if (shops.size() != 0) {
                model.addAttribute("shops", shops);
            }
        }
        return "index";
    }

    @GetMapping("/account/login")
    public String showLogin(Model model, String error) {
        if (error != null)
            model.addAttribute("error",error);
        return "accounts/login";
    }

    @PostMapping("/account/login")
    public String login(Model model,
                        String email,
                        String password,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {

        Client client = dbManager.getClientService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        Courier courier = dbManager.getCourierService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        Merchant merchant = dbManager.getMerchantService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        Employee employee = dbManager.getEmployeeService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        boolean logged = false;
        if (client != null) {
            logged = true;
            appCookies.setRoleCookie(Roles.CLIENT, response);
            appCookies.setUserIDCookie(client.getId(), response);
        } else if (courier != null) {
            logged = true;
            appCookies.setRoleCookie(Roles.COURIER, response);
            appCookies.setUserIDCookie(courier.getId(), response);
        } else if (merchant != null) {
            logged = true;
            appCookies.setRoleCookie(Roles.MERCHANT, response);
            appCookies.setUserIDCookie(merchant.getId(), response);
        } else if (employee != null) {
            logged = true;
            appCookies.setRoleCookie(Roles.EMPLOYEE, response);
            appCookies.setUserIDCookie(employee.getId(), response);
        }
        if (!logged) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/account/login";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/account/logout")
    public String logout(HttpServletResponse response) {
        Cookie user_cookie = new Cookie("user_id", "");
        Cookie role_cookie = new Cookie("role", "");
        user_cookie.setPath("/");
        role_cookie.setPath("/");
        user_cookie.setMaxAge(0);
        role_cookie.setMaxAge(0);

        response.addCookie(user_cookie);
        response.addCookie(role_cookie);
        return "redirect:/";
    }

    @GetMapping("/contacts")
    public String showContacts(Model model,
                               @CookieValue(value = "user_id", defaultValue = "") String userid,
                               @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role);
        return "contacts";
    }

    @GetMapping("/support")
    public String showSupport(Model model,
                              @CookieValue(value = "user_id", defaultValue = "") String userid,
                              @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role);
        return "support";
    }

}
