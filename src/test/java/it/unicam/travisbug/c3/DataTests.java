package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.model.users.Client;
import it.unicam.travisbug.c3.service.ClientService;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataTests {

    @Autowired
    ClientService clientService;

    @Test
    void clientRegistration() throws Exception{
        Client client = new Client();
        client.setName("Franci");
        client.setSurname("Cognome");
        client.setEmail("Cogione@gmail.com");
        client.setPassword(PasswordTool.getMD5String("password"));
        clientService.saveClient(client);
    }

}
