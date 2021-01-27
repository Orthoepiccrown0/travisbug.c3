package it.unicam.travisbug.c3.utils;

import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class AppCookies {

    public void setRoleCookie(String role, HttpServletResponse response) {
        Cookie cookie = new Cookie("role", role);
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
            logged = true;
        }
        model.addAttribute("logged", logged);
        checkRole(model, role);
        return logged;
    }

    private void checkRole(Model model, String role) {
        if (role != null && !role.equals("")) {
            model.addAttribute("role", role);
        }
    }

}
