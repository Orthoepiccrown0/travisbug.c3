package it.unicam.travisbug.c3.utils;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.model.Employee;
import it.unicam.travisbug.c3.model.Merchant;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class AppCookies {

    public void setRoleCookie(Roles role, HttpServletResponse response) {
        Cookie cookie = new Cookie("role", String.valueOf(role));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public void setUserIDCookie(String id, HttpServletResponse response) {
        Cookie cookie = new Cookie("user_id", id);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public boolean checkLogged(Model model, String userid, String role) {
        boolean logged = false;
        if (!userid.equals("") && !role.equals("")) {
            Roles roleEnum = Roles.valueOf(role);
            checkRole(model, roleEnum);
            logged = true;
        }
        model.addAttribute("logged", logged);
        return logged;
    }

    private void checkRole(Model model, Roles role) {
        if (role != null) {
            model.addAttribute("role", role);
        }
    }

    public boolean isUsedEmail(String email, DBManager dbManager) {
        Client client = dbManager.getClientService().findByEmail(email);
        Courier courier = dbManager.getCourierService().findByEmail(email);
        Merchant merchant = dbManager.getMerchantService().findByEmail(email);
        Employee employee = dbManager.getEmployeeService().findByEmail(email);
        return client != null || courier != null || merchant != null || employee != null;
    }

}
