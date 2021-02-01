package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.utils.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Profile {
    @GetMapping("/account")
    public String showAccount(Model model,
                              @CookieValue(value = "user_id", defaultValue = "") String userid,
                              @CookieValue(value = "role", defaultValue = "") Roles role) {
        if (role != null) {
            if (role == Roles.CLIENT) {

            } else if (role == Roles.COURIER) {

            } else if (role == Roles.MERCHANT) {

            } else if (role == Roles.EMPLOYEE) {

            }

        }
        return "";
    }
}
