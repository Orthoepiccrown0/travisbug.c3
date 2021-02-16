package it.unicam.travisbug.c3.controller.personal.area.client;

import it.unicam.travisbug.c3.model.users.Client;
import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClientArea {

    private DBManager dbManager;

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/deleteClient")
    public String deleteClient(@CookieValue(value = "user_id", defaultValue = "") String userid,
                               HttpServletResponse response) {
        Client c = dbManager.getClientService().findById(userid).orElseThrow();
        dbManager.getClientService().deleteClient(c);

        return "redirect:/account/logout";
    }

    @GetMapping("/account/orders")
    public String showOrders(Model model,
                             @CookieValue(value = "user_id", defaultValue = "") String userid,
                             @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);

        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        List<Order> orders = dbManager.getOrderService().getAll(client);
        orders.removeIf(order -> !order.isVisible());
        orders.removeIf(order -> order.getShipping().getAddress() == null);
        if (orders.size() == 0)
            orders = null;
        model.addAttribute("orders", orders);
        return "accounts/client/orders";
    }

    @GetMapping("/account/orders/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        Order order = dbManager.getOrderService().findById(id);
        order.setVisible(false);
        dbManager.getOrderService().saveOrder(order);
        return "redirect:/account/orders";
    }


}
