package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.model.Shipping;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.ShippingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourierArea {

    private DBManager dbManager;

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteCourier")
    public String deleteCourier(@CookieValue(value = "user_id", defaultValue = "") String userid) {
        Courier c = dbManager.getCourierService().findById(userid).orElseThrow();
        dbManager.getCourierService().deleteCourier(c);
        return "redirect:/account/logout";
    }

    @GetMapping("/courierArea")
    public String showCourierArea(Model model,
                                  @CookieValue(value = "user_id", defaultValue = "") String userid,
                                  @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);
        model.addAttribute("readyShipments", dbManager.getShippingService().getAll(ShippingStatus.ReadyForPickup));
        model.addAttribute("takenShipments", dbManager.getShippingService().getAll(ShippingStatus.Shipping));
        model.addAttribute("deliveredShipments", dbManager.getShippingService().getAll(ShippingStatus.Delivered));
        return "accounts/courier/courierArea";
    }

    @GetMapping("/courierArea/take/{id}")
    public String takeShipment(@PathVariable Integer id) {
        Shipping s = dbManager.getShippingService().findById(id);
        s.setShippingStatus(ShippingStatus.Shipping);
        dbManager.getShippingService().saveShipping(s);
        return "redirect:/courierArea";
    }

    @GetMapping("/courierArea/update/{id}")
    public String deliveredShipment(@PathVariable Integer id) {
        Shipping s = dbManager.getShippingService().findById(id);
        s.setShippingStatus(ShippingStatus.Delivered);
        dbManager.getShippingService().saveShipping(s);
        return "redirect:/courierArea";
    }
}
