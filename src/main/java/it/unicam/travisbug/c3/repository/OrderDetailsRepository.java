package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Order;
import it.unicam.travisbug.c3.model.OrderDetails;
import it.unicam.travisbug.c3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
    OrderDetails findByProductAndOrder(Product product, Order order);
}
