package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order o);

    List<Order> getAll();

}
