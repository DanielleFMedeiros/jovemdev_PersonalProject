package br.com.trier.projpessoal.surveillance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import br.com.trier.projpessoal.surveillance.BaseTests;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.dto.ClientDTO;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class ClientServiceTest extends BaseTests {

	@Autowired
	ClientService clientService;

	@Autowired
	private RestTemplate rest;

	@Test
	@DisplayName("Test fetch user by ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void findByIdTest() {
		var client = clientService.findById(3);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		assertThat(client).isNotNull();
		assertEquals(3, client.getId());
		assertEquals("Danielle", client.getName());
		assertEquals("876.543.210-01", client.getCpf());
		assertEquals("123", client.getPassword());
	}
	
	@Test
	@DisplayName("Test fetch client by non-existent ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void findByIdNonExistentTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		var exception = assertThrows(ObjectNotFound.class, () -> {
			ResponseEntity<ClientDTO> responseEntity = rest.exchange("/client/20", HttpMethod.GET, requestEntity,
					ClientDTO.class);
		});
		assertEquals("Cliente com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test list all - Error")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void listAllNonExistTest() {
		List<Client> list = clientService.listAll();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		clientService.delete(3);
		clientService.delete(4);
		var exception = assertThrows(ObjectNotFound.class, () -> clientService.listAll());
		assertEquals("Nenhum cliente encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test list all")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void listAllTest() {
		List<Client> list = clientService.listAll();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		assertEquals(10, list.size());
		assertEquals(3, list.get(0).getId());

	}

	@Test
	@DisplayName("Test search for client by name that starts with")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void findClientByNameStartsWithTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		List<Client> list = clientService.findByNameContainsIgnoreCase("Gab");
		assertEquals(2, list.size());
	}

	@Test
	@DisplayName("Test search for client by name that starts with - Erro")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void findClientByNameStartsWithErroTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		var exception = assertThrows(ObjectNotFound.class, () -> clientService.findByNameContainsIgnoreCase("J"));
		assertEquals("Nenhum cliente encontrado com nome contendo: J", exception.getMessage());
	}

	@Test
	@DisplayName("Test change user")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void UpdateClientTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		var client = new Client(11, "Gabriella", "621.035.897-84", "password");
		clientService.update(client);
		client = clientService.findById(11);
		assertThat(client).isNotNull();
		assertEquals(11, client.getId());
		assertEquals("Gabriella", client.getName());
		assertEquals("621.035.897-84", client.getCpf());
		assertEquals("password", client.getPassword());
	}
	
	@Test
	@DisplayName("Test delete user")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void DeleteClientTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		clientService.delete(3);
		List<Client> list = clientService.listAll();
		assertEquals(9, list.size());
		assertEquals(4, list.get(0).getId());
	}

	
	@Test
	@DisplayName("Test delete non-existent user")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void deleteNonExistentTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		var exception = assertThrows(ObjectNotFound.class, () -> clientService.findById(20));
		assertEquals("Cliente com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test add user")
	void insertClientTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		var client = new Client(null, "Fernando", "165.746.831-11", "password");
		clientService.insert(client);
		client = clientService.findById(1);
		assertThat(client).isNotNull();
		assertEquals(1, client.getId());
		assertEquals("Fernando", client.getName());
		assertEquals("165.746.831-11", client.getCpf());
		assertEquals("password", client.getPassword());
	}
	
	@Test
	@DisplayName("Test change non-existent user")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void updateClientNonExistentTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		var client = new Client(20, "Fernando", "165.746.831-11","123");

		assertThrows(ObjectNotFound.class, () -> clientService.update(client), "Client 6 not found");
	}


	@Test
	@DisplayName("Test save user")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	void salvarTest() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();

		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		var client = new Client(1, "Fernanda", "621.035.897-84", "123");
		clientService.insert(client);
		assertEquals(1, client.getId());
		assertEquals("Fernanda", client.getName());
		assertEquals("621.035.897-84", client.getCpf());
		assertEquals("123", client.getPassword());
	}

}
