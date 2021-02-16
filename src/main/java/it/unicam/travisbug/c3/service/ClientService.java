package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.users.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void saveClient(Client client);

    List<Client> getAll();

    Optional<Client> findById(String id);

    Client findByEmailAndPass(String email, String password);

    Client findByEmail(String email);

    void deleteClient(Client client);

}
