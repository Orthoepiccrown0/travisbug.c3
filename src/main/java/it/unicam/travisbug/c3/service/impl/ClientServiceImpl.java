package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.repository.ClientRepository;
import it.unicam.travisbug.c3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    @Qualifier("clientRepository")
    @Autowired
    private ClientRepository clientRepository;

    private static ClientServiceImpl clientService;

    private ClientServiceImpl(){

    }

    @Bean
    public static ClientService getClientServiceInstance() {
        if (clientService == null)
            clientService = new ClientServiceImpl();
        return clientService;
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
}
