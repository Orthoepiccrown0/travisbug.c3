package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.model.order.OrderDetails;
import it.unicam.travisbug.c3.model.shop.Product;

import java.util.List;

public interface OrderDetailsService {

    void saveOrderDetails(OrderDetails d);

    List<OrderDetails> getAll();

    OrderDetails findByProductAndOrder(Product product, Order order);

    OrderDetails findById(String id);

    void delete(OrderDetails orderDetails);
}
