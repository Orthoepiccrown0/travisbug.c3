package it.unicam.travisbug.c3.rest;

import it.unicam.travisbug.c3.model.Order;
import it.unicam.travisbug.c3.model.OrderDetails;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartREST {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @PostMapping("/cart/change/quantity")
    public String changeAmount(String order_id, Integer quantity) {
        OrderDetails orderDetails = dbManager.getOrderDetailsService().findById(order_id);
        Order order = orderDetails.getOrder();
        orderDetails.setQuantity(quantity);
        order.changeItem(orderDetails);
        dbManager.getOrderDetailsService().saveOrderDetails(orderDetails);
        order.updateAmount();
        dbManager.getOrderService().saveOrder(order);
        return "$" + String.format("%.2f", order.getAmount());
    }

}
