package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    void saveOrderDetails(OrderDetails d);

    List<OrderDetails> getAll();

}
