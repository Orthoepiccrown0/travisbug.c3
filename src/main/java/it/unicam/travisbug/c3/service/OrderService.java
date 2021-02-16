package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.users.Client;
import it.unicam.travisbug.c3.model.order.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order o);

    void deleteOrder(Order o);

    List<Order> getAll();

    List<Order> getAll(Client client);

    List<Order> findByClient(Client client);

    Order findById(String id);

}
