package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.OrderDetails;
import it.unicam.travisbug.c3.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Qualifier("orderDetailsRepository")
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public void saveOrderDetails(OrderDetails d) {
        orderDetailsRepository.save(d);
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }
}
