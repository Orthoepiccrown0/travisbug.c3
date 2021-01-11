package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
