package br.com.trier.projpessoal.surveillance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import br.com.trier.projpessoal.surveillance.BaseTests;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
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
        var exception = assertThrows(ObjectNotFound.class, () -> clientService.findById(20));
        assertEquals("Cliente com ID 20 não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Test fetch user by Cpf")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void findByCpfTest() {
        String cpf = "876.543.210-01";

        Optional<Client> optionalClient = clientService.findByCpf(cpf);

        assertTrue(optionalClient.isPresent());

        Client client = optionalClient.get();

        assertEquals(cpf, client.getCpf());
    }

    @Test
    @DisplayName("Test fetch user by non-existing Cpf")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void findByNonExistingCpfTest() {
        String cpf = "111.222.333-44";

        assertThrows(ObjectNotFound.class, () -> clientService.findByCpf(cpf));
    }

    @Test
    @DisplayName("Test list all - Error")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void listAllNonExistTest() {
        clientService.delete(3);
        clientService.delete(4);
        clientService.delete(5);
        clientService.delete(6);
        clientService.delete(7);
        clientService.delete(8);
        clientService.delete(9);
        clientService.delete(10);
        clientService.delete(11);
        clientService.delete(12);

        var exception = assertThrows(ObjectNotFound.class, () -> clientService.listAll());
        assertEquals("Nenhum cliente encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Test list all")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void listAllTest() {
        List<Client> list = clientService.listAll();
        assertEquals(10, list.size());
        assertEquals(3, list.get(0).getId());
    }

    @Test
    @DisplayName("Test search for client by name that starts with")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void findClientByNameStartsWithTest() {
        List<Client> list = clientService.findByNameContainsIgnoreCase("Gab");
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Test search for client by name that starts with - Erro")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void findClientByNameStartsWithErroTest() {
        var exception = assertThrows(ObjectNotFound.class, () -> clientService.findByNameContainsIgnoreCase("J"));
        assertEquals("Nenhum cliente encontrado com nome contendo: J", exception.getMessage());
    }

    @Test
    @DisplayName("Test change user")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void UpdateClientTest() {
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
        clientService.delete(3);
        List<Client> list = clientService.listAll();
        assertEquals(9, list.size());
        assertEquals(4, list.get(0).getId());
    }

    @Test
    @DisplayName("Test delete non-existent user")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void deleteNonExistentTest() {
        var exception = assertThrows(ObjectNotFound.class, () -> clientService.findById(20));
        assertEquals("Cliente com ID 20 não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Test add user")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void insertClientTest() {
        var client = new Client(null, "Fernanda", "621.035.897-84", "123");
        clientService.insert(client);
        assertEquals(1, client.getId());
        assertEquals("Fernanda", client.getName());
        assertEquals("621.035.897-84", client.getCpf());
        assertEquals("123", client.getPassword());
    }

    @Test
    @DisplayName("Test change non-existent user")
    @Sql({ "classpath:/resources/sqls/client.sql" })
    void updateClientNonExistentTest() {
        var client = new Client(20, "Fernando", "165.746.831-11", "123");

        assertThrows(ObjectNotFound.class, () -> clientService.update(client));
    }
    
  

}
