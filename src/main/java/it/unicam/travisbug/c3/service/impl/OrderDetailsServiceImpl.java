package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.model.order.OrderDetails;
import it.unicam.travisbug.c3.model.shop.Product;
import it.unicam.travisbug.c3.repository.OrderDetailsRepository;
import it.unicam.travisbug.c3.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailsService")
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Qualifier("orderDetailsRepository")
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public void setOrderDetailsRepository(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public void saveOrderDetails(OrderDetails d) {
        orderDetailsRepository.save(d);
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails findByProductAndOrder(Product product, Order order) {
        return orderDetailsRepository.findByProductAndOrder(product, order);
    }

    @Override
    public OrderDetails findById(String id) {
        return orderDetailsRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(OrderDetails orderDetails) {
        orderDetailsRepository.delete(orderDetails);
    }
}
