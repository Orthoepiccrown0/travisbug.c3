package it.unicam.travisbug.c3.utils;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;

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

    public void checkLogged(Model model,
                             @CookieValue(value = "user_id", defaultValue = "") String userid,
                             @CookieValue(value = "role", defaultValue = "") String role){
        String logged = "guest";
        if(!userid.equals("") && !role.equals("")){
            logged = "logged";
        }
        model.addAttribute("logged",logged);
    }

    public void checkRole(Model model,
                          @CookieValue(value = "role", defaultValue = "") String role){
        if(role != null && !role.equals("")){
            model.addAttribute("role",role);
        }
    }

}
