package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Order;
import it.unicam.travisbug.c3.repository.OrderRepository;
import it.unicam.travisbug.c3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Qualifier("orderRepository")
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order o) {
        orderRepository.save(o);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
