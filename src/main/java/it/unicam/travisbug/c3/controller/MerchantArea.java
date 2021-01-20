package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MerchantArea {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/merchantArea")
    public String showMerchantArea(Model model){
        return "merchantArea";
    }

}
