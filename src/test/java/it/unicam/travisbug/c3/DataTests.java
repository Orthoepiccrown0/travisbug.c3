package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.repository.ClientRepository;
import it.unicam.travisbug.c3.service.ClientService;
import it.unicam.travisbug.c3.service.impl.ClientServiceImpl;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataTests {

    @Autowired
    ClientRepository clientRepository;

    @Test
    void clientRegistration() throws Exception{
        Client client = new Client();
        client.setName("Franci");
        client.setSurname("Cognome");
        client.setEmail("Cogione@gmail.com");
        client.setPassword(PasswordTool.getMD5String("password"));
//        ClientService clientService = ClientServiceImpl.getClientServiceInstance();
//        clientService.saveClient(client);
//        clientService.saveClient(client);
        clientRepository.save(client);
    }

}
