package it.unicam.travisbug.c3.controller.personal.area.client;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.ShippingStatus;
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

    @GetMapping("account/orders/process/{id}")
    public String process(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        model.addAttribute("id", id);
        Order order = dbManager.getOrderService().findById(id);
        if(order.getShipping().getAddress()==null){
            redirectAttributes.addAttribute("shipping_error", true);
            return "redirect:/account/cart";
        }
        order.setStatus("Confirmed");
        dbManager.getOrderService().saveOrder(order);
        return "redirect:/account/orders";
    }

    @GetMapping("account/cart")
    public String showShoppingCart(Model model,
                                   @CookieValue(value = "user_id", defaultValue = "") String userid,
                                   @CookieValue(value = "role", defaultValue = "") String role,
                                   String shop_id,
                                   boolean shipping_error) {
        appCookies.checkLogged(model, userid, role);

        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        Order cart_order = dbManager.getOrderService().findByClientAndStatus(client, "Pending");
        List<Address> addresses = dbManager.getAddressService().getAll();

        if (cart_order !=null && cart_order.getOrderDetails().size() != 0) {
            List<OrderDetails> cart = new ArrayList<>(cart_order.getOrderDetails());
            double amount = 0;
            for (OrderDetails tmp : cart) {
                amount += tmp.getProduct().getPrice() * tmp.getQuantity();
            }

            model.addAttribute("cart", cart);
            model.addAttribute("amount", String.format("%.2f", amount));
            model.addAttribute("order_id", cart_order.getId());
            model.addAttribute("shop_id", shop_id);
            model.addAttribute("addresses", addresses);
            if(shipping_error)
                model.addAttribute("shipping_error", shipping_error);
            if (cart_order.getShipping().getAddress() != null)
                model.addAttribute("selected_shipping", cart_order.getShipping().getAddress().getId());
            else
                model.addAttribute("selected_shipping", "null");
        }
        return "client/shoppingCart";
    }

    @GetMapping("account/cart/add/{product_id}/{shop_id}")
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
        return "redirect:/account/cart";
    }

    @GetMapping("account/cart/remove/{item_id}")
    public String remove(Model model,
                         @CookieValue(value = "user_id", defaultValue = "") String userid,
                         @PathVariable String item_id) {
        Client client = dbManager.getClientService().findById(userid).orElseThrow();
        Order order = getOrder(client);
        OrderDetails orderDetails = dbManager.getOrderDetailsService().findById(item_id);
        order.removeItem(orderDetails);
        dbManager.getOrderDetailsService().delete(orderDetails);
        dbManager.getOrderService().saveOrder(order);
        return "redirect:/account/cart";
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
            if (orderDetails.getProduct().getSupply() > (orderDetails.getQuantity() + 1))
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

            Shipping shipping = new Shipping();
            shipping.setShipping_order(order);
            shipping.setShippingStatus(ShippingStatus.Pending);
            dbManager.getShippingService().saveShipping(shipping);
            order.setShipping(shipping);
            dbManager.getOrderService().saveOrder(order);

        }
        return order;
    }




}
