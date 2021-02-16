package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.users.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, String> {

    Courier findByEmailAndPassword(String email, String password);

    Courier findByEmail(String email);
}
