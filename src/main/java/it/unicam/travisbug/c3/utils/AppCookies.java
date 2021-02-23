package it.unicam.travisbug.c3.utils;

import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.model.order.OrderDetails;
import it.unicam.travisbug.c3.model.shop.Product;
import it.unicam.travisbug.c3.model.users.Client;
import it.unicam.travisbug.c3.model.users.Courier;
import it.unicam.travisbug.c3.model.users.Employee;
import it.unicam.travisbug.c3.model.users.Merchant;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AppCookies {

    private static AppCookies appCookies;

    private AppCookies() {
    }

    public static AppCookies getInstance() {
        if (appCookies == null)
            appCookies = new AppCookies();
        return appCookies;
    }

    public void setRoleCookie(Roles role, HttpServletResponse response) {
        Cookie cookie = new Cookie("role", String.valueOf(role));
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public void setUserIDCookie(String id, HttpServletResponse response) {
        Cookie cookie = new Cookie("user_id", id);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public boolean checkLogged(Model model, String userid, String role, DBManager dbManager) {
        boolean logged = false;
        if (!userid.equals("") && !role.equals("")) {
            Roles roleEnum = Roles.valueOf(role);
            checkRole(model, roleEnum, userid, dbManager);
            logged = true;
        }
        model.addAttribute("logged", logged);
        return logged;
    }

    private void checkRole(Model model, Roles role, String userid, DBManager dbManager) {
        if (role != null) {
            if (role == Roles.MERCHANT) {
                model.addAttribute("balance", prepareBalance(userid, dbManager));
            }
            model.addAttribute("role", role);
        }
    }

    private String prepareBalance(String userid, DBManager dbManager) {
        Merchant merchant = dbManager.getMerchantService().findById(userid).orElseThrow();
        List<Order> orders = new ArrayList<>();
        for (Product p : merchant.getProduct()) {
            for (OrderDetails o : p.getOrderDetails()) {
                if (o.getOrder().getShipping().getShippingStatus() != ShippingStatus.Pending)
                    orders.add(o.getOrder());
            }
        }
        double balance = 0.0;
        for (Order o : orders) {
            balance = balance + (o.getAmount() - o.getShipping().getAddress().getShipCharge());
        }
        return String.format("%.2f", balance);
    }

    public boolean isUsedEmail(String email, DBManager dbManager) {
        Client client = dbManager.getClientService().findByEmail(email);
        Courier courier = dbManager.getCourierService().findByEmail(email);
        Merchant merchant = dbManager.getMerchantService().findByEmail(email);
        Employee employee = dbManager.getEmployeeService().findByEmail(email);
        return client != null || courier != null || merchant != null || employee != null;
    }

}
