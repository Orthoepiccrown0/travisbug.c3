package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.Roles;
import it.unicam.travisbug.c3.utils.ShippingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class EmployeeArea {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@CookieValue(value = "user_id", defaultValue = "") String userid) {
        Employee e = dbManager.getEmployeeService().findById(userid).orElseThrow();
        dbManager.getEmployeeService().deleteEmployee(e);
        return "redirect:/user_logout";
    }

    @GetMapping("/employeeArea")
    public String showEmployeeArea(Model model,
                                   @CookieValue(value = "user_id", defaultValue = "") String userid,
                                   @CookieValue(value = "role", defaultValue = "") String role){
        appCookies.checkLogged(model, userid, role);

        List<Order> user_orders = new ArrayList<>();

        Set<Product> products = dbManager.getEmployeeService().findById(userid).orElseThrow().getShop().getMerchant().getProduct();
        for (Product p: products) {
            for(OrderDetails od : p.getOrderDetails()){
                if(od.getOrder().getShipping().getShippingStatus().equals(ShippingStatus.Confirmed)) {
                    user_orders.add(od.getOrder());
                }
            }
        }

        model.addAttribute("user_orders", user_orders);
        return "employeeArea";
    }

    @GetMapping("/employeeArea/update/{id}")
    public String updateOrder(@PathVariable String id){
        Order order = dbManager.getOrderService().findById(id);
        Shipping shipping = order.getShipping();
        shipping.setShippingStatus(ShippingStatus.ReadyForPickup);
        dbManager.getShippingService().saveShipping(shipping);
        return "redirect:/employeeArea";
    }

}
