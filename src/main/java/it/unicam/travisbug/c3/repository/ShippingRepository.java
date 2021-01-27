package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Shipping;
import it.unicam.travisbug.c3.utils.ShippingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

    List<Shipping> findAllByShippingStatus(ShippingStatus shippingStatus);
}
