package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourierArea {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteCourier")
    public String deleteCourier(@CookieValue(value = "user_id", defaultValue = "") String userid){
        Courier c = dbManager.getCourierService().findById(userid).orElseThrow();
        dbManager.getCourierService().deleteCourier(c);
        return "redirect:/user_logout";
    }

}
