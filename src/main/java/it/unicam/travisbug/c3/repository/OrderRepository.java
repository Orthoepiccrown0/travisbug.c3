package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByClientAndStatus(Client client, String status);
}
