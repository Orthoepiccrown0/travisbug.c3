package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.order.Order;
import it.unicam.travisbug.c3.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByClient(Client client);

    List<Order> findAllByClientOrderByDateDesc(Client client);

}
