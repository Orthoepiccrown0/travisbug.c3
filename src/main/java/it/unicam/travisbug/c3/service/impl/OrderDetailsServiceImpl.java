package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.OrderDetails;
import it.unicam.travisbug.c3.repository.OrderDetailsRepository;
import it.unicam.travisbug.c3.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailsService")
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
