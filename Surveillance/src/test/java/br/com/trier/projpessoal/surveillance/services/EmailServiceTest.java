package br.com.trier.projpessoal.surveillance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.projpessoal.surveillance.BaseTests;
import br.com.trier.projpessoal.surveillance.domain.Email;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.dto.EmailDTO;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class EmailServiceTest extends BaseTests {

	@Autowired
	EmailService emailService;

	@Test
	@DisplayName("Test fetch email by ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findByIdTest() {
		var email = emailService.findById(3);
		assertThat(email).isNotNull();
		assertEquals(3, email.getId());
		assertEquals("danielle@gmail.com", email.getEmail());
		assertEquals(3, email.getClient().getId_client());
	}

	@Test
	@DisplayName("Test fetch email by non-existent ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findByIdNonExistentTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> emailService.findById(20));
		assertEquals("Email com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test find email by description")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findByNeighborhoodTest() {
		String description = "danielle@gmail.com";
		List<Email> emails = emailService.findByEmailContainsIgnoreCase(description);

		assertFalse(emails.isEmpty());
		Email email = emails.get(0);

		assertNotNull(email);
		assertEquals(description, email.getEmail());
	}

	@Test
	@DisplayName("Test fetch client by non-existing Neighborhood")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findByNonExistingNeighborhoodTest() {
		String description = "danielle1@gmail.com";

		assertThrows(ObjectNotFound.class, () -> emailService.findByEmailContainsIgnoreCase(description));
	}

	@Test
	@DisplayName("Test list all - Error")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void listAllNonExistTest() {
		emailService.delete(3);
		emailService.delete(4);
		emailService.delete(5);

		var exception = assertThrows(ObjectNotFound.class, () -> emailService.listAll());
		assertEquals("Nenhum endereço encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test list all")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void listAllTest() {
		List<Email> list = emailService.listAll();
		assertEquals(3, list.size());
		assertEquals(3, list.get(0).getId());
	}

	@Test
	@DisplayName("Test search for email by street that starts with")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findEmailByStreetStartsWithTest() {
		List<Email> list = emailService.findByEmailContainsIgnoreCase("dani");
		assertEquals(1, list.size());
	}

	@Test
	@DisplayName("Test search for email by street that starts with - Erro")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void findEmailByStreetStartsWithErroTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> emailService.findByEmailContainsIgnoreCase("J"));
		assertEquals("Nenhum email encontrado com nome contendo: J", exception.getMessage());
	}

	@Test
	@DisplayName("Test change email")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void UpdateEmailTest() {
		var email = new Email(3, "Teste@gmail",new Client(3, null,null,null));

		emailService.update(email);
		email = emailService.findById(3);
		assertThat(email).isNotNull();
		assertEquals(3, email.getId());
		assertEquals("teste@gmail", email.getEmail());
		assertEquals(3, email.getClient().getId_client());
		
	}

	@Test
	@DisplayName("Test delete client")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void DeleteEmailTest() {
		emailService.delete(3);
		List<Email> list = emailService.listAll();
		assertEquals(2, list.size());
		assertEquals(4, list.get(0).getId());
	}

	@Test
	@DisplayName("Test delete non-existent client")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void deleteNonExistentTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> emailService.findById(20));
		assertEquals("Email com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test add email")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })	
	void insertEmailTest() {
		var email = new Email(null, "Bianca@gmail",new Client(8, null,null,null));
		emailService.insert(email);
		assertEquals(1, email.getId());
		assertEquals("Bianca@gmail", email.getEmail());
		assertEquals(8, email.getClient().getId_client());
	}

	@Test
	@DisplayName("Test change non-existent email")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/email.sql" })
	void updateEmailNonExistentTest() {
		var emailDto = new EmailDTO(20, "danielle@gmail.com", 3);
		var email = new Email(emailDto);

		assertThrows(ObjectNotFound.class, () -> emailService.update(email));
	}

}