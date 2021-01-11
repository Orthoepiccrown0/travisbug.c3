package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Order;
import it.unicam.travisbug.c3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Qualifier("orderRepository")
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order o) {
        orderRepository.save(o);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
