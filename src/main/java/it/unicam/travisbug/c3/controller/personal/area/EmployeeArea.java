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

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@CookieValue(value = "user_id", defaultValue = "") String userid) {
        Employee e = dbManager.getEmployeeService().findById(userid).orElseThrow();
        dbManager.getEmployeeService().deleteEmployee(e);
        return "redirect:/account/logout";
    }

    @GetMapping("/employeeArea")
    public String showEmployeeArea(Model model,
                                   @CookieValue(value = "user_id", defaultValue = "") String userid,
                                   @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);

        Employee employee = dbManager.getEmployeeService().findById(userid).orElseThrow();
        if (employee.getStatus().equals("Approved")) {
            List<Order> user_orders = getOrdersByShippingStatus(userid, ShippingStatus.Confirmed);
            List<Order> confirmedShopOrders = getOrdersByShippingStatus(userid, ShippingStatus.ConfirmedShop);

            user_orders.removeIf(order -> !order.getShops().contains(employee.getShop()));
            confirmedShopOrders.removeIf(order -> !order.getShops().contains(employee.getShop()));

            Set<Product> shop_products = employee.getShop().getMerchant().getProduct();
            model.addAttribute("shop_products", shop_products);
            model.addAttribute("user_orders", user_orders);
            model.addAttribute("confirmedShopOrders", confirmedShopOrders);
        }
        return "employeeArea";
    }

    private List<Order> getOrdersByShippingStatus(String userid, ShippingStatus shippingStatus) {
        List<Order> confirmedOrders = new ArrayList<>();
        Set<Product> productShop = dbManager.getEmployeeService().
                findById(userid).
                orElseThrow().
                getShop().
                getMerchant().
                getProduct();
        for (Product p : productShop) {
            for (OrderDetails od : p.getOrderDetails()) {
                if (od.getOrder().getShipping().getShippingStatus().equals(shippingStatus)) {
                    confirmedOrders.add(od.getOrder());
                }
            }
        }
        return confirmedOrders;
    }

    @GetMapping("/employeeArea/update/{status}/{id}")
    public String updateOrder(@PathVariable String status,
                              @PathVariable String id,
                              @CookieValue(value = "user_id", defaultValue = "") String userid) {
        Order order = dbManager.getOrderService().findById(id);
        Shipping shipping = order.getShipping();
        Employee employee = dbManager.getEmployeeService().findById(userid).orElseThrow();
        order.removeShop(employee.getShop());
        if (order.getShops().size() == 0) {    //if others are ready
            if (status.equals(ShippingStatus.Confirmed.toString())) {
                shipping.setShippingStatus(ShippingStatus.ReadyForPickup);
            } else if (status.equals(ShippingStatus.ConfirmedShop.toString())) {
                shipping.setShippingStatus(ShippingStatus.ReadyForClientPickup);
            }
            dbManager.getShippingService().saveShipping(shipping);
        }
        dbManager.getOrderService().saveOrder(order);
        return "redirect:/employeeArea";
    }

}
