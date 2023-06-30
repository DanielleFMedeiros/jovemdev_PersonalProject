package br.com.trier.projpessoal.surveillance.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.projpessoal.surveillance.BaseTests;
import br.com.trier.projpessoal.surveillance.domain.Contract;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class ContractServiceTest extends BaseTests {

    @Autowired
    ContractService contractService;

    @Test
    @DisplayName("Test insert contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testInsert() {
        Contract newContract = new Contract(6, LocalDate.parse("2023-06-21"), LocalDate.parse("2023-06-23"), 75.0, 5, 6);
        Contract savedContract = contractService.insert(newContract);
        assertNotNull(savedContract);
        assertEquals(6, savedContract.getId());
    }

    @Test
    @DisplayName("Test list all contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testListAll() {
        List<Contract> contracts = contractService.listAll();
        assertEquals(3, contracts.size());
    }

    @Test
    @DisplayName("Test findbyId contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testFindById() {
        Contract contract = contractService.findById(3);
        assertNotNull(contract);
        assertEquals(3, contract.getId());
        assertThrows(ObjectNotFound.class, () -> contractService.findById(100));
    }

    @Test
    @DisplayName("Test update contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testUpdate() {
        Contract existingContract = contractService.findById(3);
        existingContract.setPrice(60.0);
        Contract updatedContract = contractService.update(existingContract);
        assertEquals(60, updatedContract.getPrice());
        // Verifique outros atributos atualizados se necessário

        // Testar o caso em que o contrato não existe
        Contract nonExistingContract = new Contract(1000, "2025-06-20", "2026-06-21", 100.0, 3, 3);
        assertThrows(ObjectNotFound.class, () -> contractService.update(nonExistingContract));
    }

    @Test
    @DisplayName("Test delete contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testDelete() {
        contractService.delete(3);
        assertThrows(ObjectNotFound.class, () -> contractService.findById(3));

        // Testar o caso em que o contrato não existe
        assertThrows(ObjectNotFound.class, () -> contractService.delete(100));
    }

    @Test
    @DisplayName("Test findByPrice contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testFindByPrice() {
        List<Contract> contracts = contractService.findByPrice(50.0);
        assertEquals(2, contracts.size());
        // Verifique outros contratos se necessário

        // Testar o caso em que nenhum contrato é encontrado
        assertThrows(ObjectNotFound.class, () -> contractService.findByPrice(200.0));
    }

    @Test
    @DisplayName("Test findByDateInicial contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testFindByDateInitial() {
        // Testar o método findByDateInitial

        LocalDate date = LocalDate.parse("2023-06-29");
        ZonedDateTime dateInitial = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
        List<Contract> contracts = contractService.findByDateInitial(dateInitial);
        assertEquals(1, contracts.size());
        // Verifique outros contratos se necessário

        // Testar o caso em que nenhum contrato é encontrado

        LocalDate nonExistingDate = LocalDate.parse("2023-06-30");
        ZonedDateTime nonExistingDateInitial = ZonedDateTime.of(nonExistingDate.atStartOfDay(), ZoneId.systemDefault());
        assertThrows(ObjectNotFound.class, () -> contractService.findByDateInitial(nonExistingDateInitial));
    }

    @Test
    @DisplayName("Test findByDateFinal contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testFindByDateFinal() {
        LocalDate date = LocalDate.parse("2024-06-29");
        ZonedDateTime dateFinal = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
        List<Contract> contracts = contractService.findByDateFinal(dateFinal);
        assertEquals(1, contracts.size());
        // Verifique outros contratos se necessário

        // Testar o caso em que nenhum contrato é encontrado
        LocalDate nonExistingDate = LocalDate.parse("2024-06-30");
        ZonedDateTime nonExistingDateFinal = ZonedDateTime.of(nonExistingDate.atStartOfDay(), ZoneId.systemDefault());
        assertThrows(ObjectNotFound.class, () -> contractService.findByDateFinal(nonExistingDateFinal));
    }

    @Test
    @DisplayName("Test FindByDataBetween contract")
    @Sql({ "classpath:/resources/sqls/client.sql", "classpath:/resources/sqls/contract.sql" })
    public void testFindByDataBetween() {
        // Testar o método findByDataBetween
        LocalDate dateInicial1 = LocalDate.parse("2023-06-28");
        LocalDate dateFinal1 = LocalDate.parse("2023-06-30");
        ZonedDateTime dateInicial = ZonedDateTime.of(dateInicial1.atStartOfDay(), ZoneId.systemDefault());
        ZonedDateTime dateFinal = ZonedDateTime.of(dateFinal1.atStartOfDay(), ZoneId.systemDefault());
        List<Contract> contracts = contractService.findByDateBetween(dateInicial, dateFinal);
        assertEquals(2, contracts.size());
        // Verifique outros contratos se necessário

        // Testar o caso em que nenhum contrato é encontrado
        LocalDate nonExistingdateInicial = LocalDate.parse("2022-06-28");
        LocalDate nonExistingdateFinal = LocalDate.parse("2022-06-30");
        ZonedDateTime nonExistingDataInicial = ZonedDateTime.of(nonExistingdateInicial.atStartOfDay(),
                ZoneId.systemDefault());
        ZonedDateTime nonExistingDataFinal = ZonedDateTime.of(nonExistingdateFinal.atStartOfDay(),
                ZoneId.systemDefault());
        assertThrows(ObjectNotFound.class, () -> contractService.findByDateBetween(nonExistingDataInicial,
                nonExistingDataFinal));
    }
}
