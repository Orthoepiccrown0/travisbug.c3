package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Home {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/")
    public String showHome(Model model,
                           @CookieValue(value = "user_id", defaultValue = "") String userid,
                           @CookieValue(value = "role", defaultValue = "") String role) {
        checkLogged(model, userid, role);
        return "index";
    }

    @GetMapping("/user_login")
    public String showLogin() {
        return "accounts/login";
    }

    @PostMapping("/user_login")
    public String login(Model model,
                        String email,
                        String password,
                        @RequestParam(value = "checkboxName", required = false) String remember,
                        HttpServletResponse response) {
        boolean rememberState = false;
        if (remember != null)
            rememberState = true;
        Client client = dbManager.getClientService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        Courier courier = dbManager.getCourierService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        Merchant merchant = dbManager.getMerchantService().findByEmailAndPass(email, PasswordTool.getMD5String(password));
        if (client != null) {
            appCookies.setRoleCookie("client", response);
            appCookies.setUserIDCookie(client.getId(), response, rememberState);
        }else if(courier != null){
            appCookies.setRoleCookie("courier", response);
            appCookies.setUserIDCookie(courier.getId(), response, rememberState);
        }else if(merchant != null){
            appCookies.setRoleCookie("merchant", response);
            appCookies.setUserIDCookie(merchant.getId(), response, rememberState);
        }
        return "redirect:/";
    }

    @GetMapping("/user_logout")
    public String logout(HttpServletResponse response){
        Cookie user_cookie = new Cookie("user_id", "");
        Cookie role_cookie = new Cookie("role", "");
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
        checkLogged(model, userid, role);
        return "contacts";
    }

    private void checkLogged(Model model,
                            @CookieValue(value = "user_id", defaultValue = "") String userid,
                            @CookieValue(value = "role", defaultValue = "") String role){
        String logged = "guest";
        if(!userid.equals("") && !role.equals("")){
            logged = "logged";
        }
        model.addAttribute("logged",logged);
    }
}
