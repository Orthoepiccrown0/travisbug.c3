package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Order;
import it.unicam.travisbug.c3.model.OrderDetails;
import it.unicam.travisbug.c3.model.Product;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.aspectj.weaver.ast.Or;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ShoppingCart {

    private final AppCookies appCookies = new AppCookies();

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("cart")
    public String showShoppingCart(Model model,
                                   @CookieValue(value = "user_id", defaultValue = "") String userid,
                                   @CookieValue(value = "role", defaultValue = "") String role,
                                   String shop_id) {
        appCookies.checkLogged(model, userid, role);
        if (shop_id != null)
            model.addAttribute("shop_id", shop_id);

        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        Order cart_order = dbManager.getOrderService().findByClientAndStatus(client, "Pending");

        if (cart_order.getOrderDetails().size() != 0) {
            List<OrderDetails> cart = new ArrayList<>(cart_order.getOrderDetails());
            double amount = 0;
            for (OrderDetails tmp : cart) {
                amount += tmp.getProduct().getPrice() * tmp.getQuantity();
            }

            model.addAttribute("cart", cart);
            model.addAttribute("amount", String.format("%.2f", amount));
        }
        return "shoppingCart";
    }

    @GetMapping("cart/add/{product_id}/{shop_id}")
    public String add(Model model,
                      @CookieValue(value = "user_id", defaultValue = "") String userid,
                      @PathVariable String product_id,
                      @PathVariable String shop_id,
                      RedirectAttributes redirectAttributes) {
        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        Product product = dbManager.getProductService().findById(Integer.parseInt(product_id));

        Order order = getOrder(client);
        OrderDetails orderDetails = getOrderDetails(product, order);

        product.addOrderDetails(orderDetails);
        order.addOrderDetails(orderDetails);
        order.updateAmount();
        dbManager.getOrderDetailsService().saveOrderDetails(orderDetails);
        dbManager.getProductService().saveProduct(product);
        dbManager.getOrderService().saveOrder(order);
        redirectAttributes.addAttribute("shop_id", shop_id);
        return "redirect:/cart";
    }

    @GetMapping("cart/remove/{item_id}")
    public String remove(Model model,
                         @CookieValue(value = "user_id", defaultValue = "") String userid,
                         @PathVariable String item_id) {
        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        Order order = getOrder(client);
        OrderDetails orderDetails = dbManager.getOrderDetailsService().findById(item_id);
        order.removeItem(orderDetails);
        dbManager.getOrderDetailsService().delete(orderDetails);
        dbManager.getOrderService().saveOrder(order);
        return "redirect:/cart";
    }

    private OrderDetails getOrderDetails(Product product, Order order) {
        OrderDetails orderDetails = dbManager.getOrderDetailsService().findByProductAndOrder(product, order);
        if (orderDetails == null) {
            orderDetails = new OrderDetails();
            orderDetails.setId(UUID.randomUUID().toString());
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setQuantity(1);
        } else {
            orderDetails.setQuantity(orderDetails.getQuantity() + 1);
        }
        return orderDetails;
    }

    private Order getOrder(Client client) {
        Order order = dbManager.getOrderService().findByClientAndStatus(client, "Pending");
        if (order == null) {
            order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setClient(client);
            order.setDate(new Date());
            order.setStatus("Pending");
            order.setAmount(0.0);
            dbManager.getOrderService().saveOrder(order);
        }
        return order;
    }


}
