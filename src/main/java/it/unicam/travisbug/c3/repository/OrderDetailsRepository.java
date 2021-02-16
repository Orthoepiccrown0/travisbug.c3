package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.model.order.OrderDetails;
import it.unicam.travisbug.c3.model.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
    OrderDetails findByProductAndOrder(Product product, Order order);

    List<OrderDetails> findAllByOrderByProduct();
}
