package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.order.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
