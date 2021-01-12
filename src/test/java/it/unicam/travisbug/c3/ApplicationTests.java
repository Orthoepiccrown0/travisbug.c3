package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.controller.Home;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private Home home;

	@Test
	void contextLoads() throws Exception{
		Assert.notNull(home,"Home is null");
	}



}
