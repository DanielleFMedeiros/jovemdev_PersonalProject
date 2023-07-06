package br.com.trier.projpessoal.surveillance.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.projpessoal.surveillance.BaseTests;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.Contract;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class ContractServiceTest extends BaseTests {

    @Autowired
    ContractService contractService;

    @Test
    @DisplayName("Test insert contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql", "classpath:/resources/sqls/contract.sql" })
    public void testInsertContract() {
        LocalDate startDate = LocalDate.of(2023, 06, 21);
        LocalDate endDate = LocalDate.of(2025, 06, 21);
        double price = 100.0;
        int id_client = 5;
        int id_address = 3;
        Contract contract = new Contract();
        var contract1 = new Contract(null, startDate, endDate, price, id_client, id_address, new Client(5, null, null, null));

        contractService.insert(contract1);
        assertNotNull(contract1.getId());
        assertEquals(startDate, contract1.getStartDate());
        assertEquals(endDate, contract1.getEndDate());
        assertEquals(price, contract1.getPrice(), 0.01); // Use a delta for double comparisons
        assertEquals(5, contract1.getClient().getId_client());
        assertEquals(3, contract1.getAddress().getId());
    }


    @Test
    @DisplayName("Test insert contract with missing start date")
    public void testInsertContractMissingStartDate() {
        LocalDate endDate = LocalDate.now().plusMonths(1);
        double price = 100.0;
        int clientId = 4;
        Client client = new Client();
        client.setId_client(clientId);

        Contract contract = new Contract();
        contract.setEndDate(endDate);
        contract.setPrice(price);
        contract.setClient(client);

        assertThrows(BreachOfIntegrity.class, () -> contractService.insert(contract));
    }

    @Test
    @DisplayName("Test insert contract with missing end date")
    public void testInsertContractMissingEndDate() {
        LocalDate startDate = LocalDate.now();
        double price = 100.0;
        int clientId = 5;
        Client client = new Client();
        client.setId_client(clientId);
        Contract contract = new Contract();
        contract.setStartDate(startDate);
        contract.setPrice(price);
        contract.setClient(client);

        assertThrows(BreachOfIntegrity.class, () -> contractService.insert(contract));
    }

    @Test
    @DisplayName("Test insert contract with negative price")
    public void testInsertContractNegativePrice() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        double price = -100.0;
        int clientId = 6;
        Client client = new Client();
        client.setId_client(clientId);
        Contract contract = new Contract();
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setPrice(price);
        contract.setClient(client);

        assertThrows(BreachOfIntegrity.class, () -> contractService.insert(contract));
    }

    @Test
    @DisplayName("Test insert contract with missing client")
    public void testInsertContractMissingClient() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);
        double price = 100.0;

        Contract contract = new Contract();
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setPrice(price);

        assertThrows(BreachOfIntegrity.class, () -> contractService.insert(contract));
    }




    @Test
    @DisplayName("Test insert contract with missing start date")
    public void testInsertMissingStartDate() {
        LocalDate dateFinal = LocalDate.parse("2023-06-23");

        Contract newContract = new Contract(6, null, dateFinal, 75.0, 5, 3);

        assertThrows(BreachOfIntegrity.class, () -> contractService.insert(newContract));
    }

    @Test
    @DisplayName("Test list all contracts")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testListAllContracts() {
        List<Contract> contracts = contractService.listAll();

        assertEquals(3, contracts.size());
    }

    @Test
    @DisplayName("Test find contract by ID")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractById() {
        Contract contract = contractService.findById(3);

        assertNotNull(contract);
        assertEquals(3, contract.getId());
    }

    @Test
    @DisplayName("Test update contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testUpdateContract() {
        Contract contract = contractService.findById(3);
        contract.setPrice(100.0);

        Contract updatedContract = contractService.update(contract);

        assertNotNull(updatedContract);
        assertEquals(100.0, updatedContract.getPrice());
    }

    @Test
    @DisplayName("Test delete contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testDeleteContract() {
        contractService.delete(4);

        assertThrows(ObjectNotFound.class, () -> contractService.findById(1));
    }
    
    @Test
    @DisplayName("Test find contracts by price")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractsByPrice() {
        double price = 100.0;
        List<Contract> contracts = contractService.findByPrice(price);

        assertEquals(1, contracts.size());
        contracts.forEach(contract -> assertEquals(price, contract.getPrice()));
    }

    @Test
    @DisplayName("Test find contracts by start date")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractsByStartDate() {
        LocalDate startDate = LocalDate.parse("2023-06-29", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Contract> contracts = contractService.findByStartDate(startDate);

        assertEquals(1, contracts.size());
        contracts.forEach(contract -> assertEquals(startDate, contract.getStartDate()));
    }

    @Test
    @DisplayName("Test find contracts by end date")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractsByEndDate() {
        LocalDate endDate = LocalDate.parse("2024-06-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Contract> contracts = contractService.findByEndDate(endDate);

        assertEquals(1, contracts.size());
        contracts.forEach(contract -> assertEquals(endDate, contract.getEndDate()));
    }

    @Test
    @DisplayName("Test find contract by CPF")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractByCpf() {
        String cpf = "876.543.210-01";
        Optional<Contract> contract = contractService.findByCpf(cpf);

        assertNotNull(contract);
        assertEquals(cpf, contract.get().getClient().getCpf());
    }

    @Test
    @DisplayName("Test find contract by non-existent CPF")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/address.sql",
            "classpath:/resources/sqls/contract.sql" })
    public void testFindContractByNonExistentCpf() {
        String cpf = "99999999999";

        assertThrows(ObjectNotFound.class, () -> contractService.findByCpf(cpf));
    }

}

