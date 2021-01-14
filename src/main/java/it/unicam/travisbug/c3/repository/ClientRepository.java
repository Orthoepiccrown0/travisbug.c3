package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ref.Cleaner;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByEmailAndPassword(String email, String password);

    Client findByEmail(String email);
}
