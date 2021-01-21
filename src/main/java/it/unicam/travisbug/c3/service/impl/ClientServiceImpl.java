package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.repository.ClientRepository;
import it.unicam.travisbug.c3.service.ClientService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clientService")
@Component
public class ClientServiceImpl implements ClientService, ApplicationContextAware {

    @Qualifier("clientRepository")
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private static ClientServiceImpl clientService;

    private ClientServiceImpl() {

    }

    public static ClientService getServiceInstance() {
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

    @Override
    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client findByEmailAndPass(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
