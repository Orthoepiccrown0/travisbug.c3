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

    public void setUserIDCookie(String id, HttpServletResponse response, Boolean remember) {
        Cookie cookie = new Cookie("user_id", id);
        if (remember)
            cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public void checkLogged(Model model, String userid, String role) {
        String logged = "guest";
        if (!userid.equals("") && !role.equals("")) {
            logged = "logged";
        }
        model.addAttribute("logged", logged);
        checkRole(model, role);
    }

    private void checkRole(Model model, String role) {
        if (role != null && !role.equals("")) {
            model.addAttribute("role", role);
        }
    }

}
