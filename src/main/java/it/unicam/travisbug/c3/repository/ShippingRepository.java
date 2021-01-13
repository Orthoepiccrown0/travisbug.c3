package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

}
