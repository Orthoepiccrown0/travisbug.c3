package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Client;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);

    List<Client> getAll();
}
