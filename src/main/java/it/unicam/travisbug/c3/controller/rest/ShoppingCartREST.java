package it.unicam.travisbug.c3.controller.rest;

import it.unicam.travisbug.c3.model.Address;
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

    @PostMapping("account/cart/change/quantity")
    public String changeAmount(String order_id, Integer quantity) {
        OrderDetails orderDetails = dbManager.getOrderDetailsService().findById(order_id);
        Order order = orderDetails.getOrder();
        orderDetails.setQuantity(quantity);
        order.changeItem(orderDetails);
        dbManager.getOrderDetailsService().saveOrderDetails(orderDetails);
        order.updateAmount();
        dbManager.getOrderService().saveOrder(order);
        if (order.getShipping().getAddress() == null)
            return new ShippingResponse(order.getAmount(), 0.0).toString();
        else
            return new ShippingResponse(order.getAmount(), order.getShipping().getAddress().getShipCharge())
                    .toString();
    }

    @PostMapping("account/cart/change/shipping")
    public String changeShipping(String order_id, Integer address_id) {
        Order order = dbManager.getOrderService().findById(order_id);
        Address address = dbManager.getAddressService().findById(address_id);
        order.getShipping().setAddress(address);
        order.updateAmount();
        dbManager.getOrderService().saveOrder(order);
        return new ShippingResponse(order.getAmount(), address.getShipCharge()).toString();
    }

    @PostMapping("account/cart/price")
    public String changeShipping(String order_id){
        Order order = dbManager.getOrderService().findById(order_id);
        if (order.getShipping().getAddress() == null)
            return new ShippingResponse(order.getAmount(), 0.0).toString();
        else
            return new ShippingResponse(order.getAmount(), order.getShipping().getAddress().getShipCharge())
                    .toString();
    }

    private static class ShippingResponse {
        private final double total;
        private final double shippingCharge;

        public ShippingResponse(double total, double shippingCharge) {
            this.total = total;
            this.shippingCharge = shippingCharge;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"total\":" + total +
                    ", \"shippingCharge\":" + shippingCharge +
                    '}';
        }
    }


}
