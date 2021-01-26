package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.model.Employee;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeArea {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@CookieValue(value = "user_id", defaultValue = "") String userid){
        Employee e = dbManager.getEmployeeService().findById(userid).orElseThrow();
        dbManager.getEmployeeService().deleteEmployee(e);
        return "redirect:/user_logout";
    }

}
