package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.service.CourierService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, String> {
    Courier findByEmail(String email);
}
