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
import br.com.trier.projpessoal.surveillance.domain.Address;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.dto.AddressDTO;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class AddressServiceTest extends BaseTests {

	@Autowired
	AddressService addressService;

	@Test
	@DisplayName("Test fetch client by ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findByIdTest() {
		var address = addressService.findById(3);
		assertThat(address).isNotNull();
		assertEquals(3, address.getId());
		assertEquals("Avenida Marcolino Martins Cabral", address.getStreet());
		assertEquals("Centro", address.getNeighborhood());
		assertEquals(1, address.getNumber());
		assertEquals(3, address.getClient().getId_client());
	}

	@Test
	@DisplayName("Test fetch address by non-existent ID")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findByIdNonExistentTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> addressService.findById(20));
		assertEquals("Endereço com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test find addresses by neighborhood")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findByNeighborhoodTest() {
		String neighborhood = "Centro";
		List<Address> addresses = addressService.findByNeighborhood(neighborhood);

		assertFalse(addresses.isEmpty());
		Address address = addresses.get(0);

		assertNotNull(address);
		assertEquals(neighborhood, address.getNeighborhood());
	}

	@Test
	@DisplayName("Test fetch client by non-existing Neighborhood")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findByNonExistingNeighborhoodTest() {
		String neighborhood = "Bairro 1";

		assertThrows(ObjectNotFound.class, () -> addressService.findByNeighborhood(neighborhood));
	}

	@Test
	@DisplayName("Test list all - Error")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void listAllNonExistTest() {
		addressService.delete(3);
		addressService.delete(4);
		addressService.delete(5);

		var exception = assertThrows(ObjectNotFound.class, () -> addressService.listAll());
		assertEquals("Nenhum endereço encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test list all")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void listAllTest() {
		List<Address> list = addressService.listAll();
		assertEquals(3, list.size());
		assertEquals(3, list.get(0).getId());
	}

	@Test
	@DisplayName("Test search for address by street that starts with")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findAddressByStreetStartsWithTest() {
		List<Address> list = addressService.findByStreetContainsIgnoreCase("Avenida");
		assertEquals(2, list.size());
	}

	@Test
	@DisplayName("Test search for address by street that starts with - Erro")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void findAddressByStreetStartsWithErroTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> addressService.findByStreetContainsIgnoreCase("J"));
		assertEquals("Nenhum endereço encontrado com nome contendo: J", exception.getMessage());
	}

	@Test
	@DisplayName("Test change address")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void UpdateAddressTest() {
		var address = new Address(3, "Avenida Marcolino Martins Cabral", "Centro", 2, new Client(3, null, null, null));
		addressService.update(address);
		address = addressService.findById(3);
		assertThat(address).isNotNull();
		assertEquals(3, address.getId());
		assertEquals("Avenida Marcolino Martins Cabral", address.getStreet());
		assertEquals("Centro", address.getNeighborhood());
		assertEquals(2, address.getNumber());
		assertEquals(3, address.getClient().getId_client());
	}

	@Test
	@DisplayName("Test delete client")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void DeleteAddressTest() {
		addressService.delete(3);
		List<Address> list = addressService.listAll();
		assertEquals(2, list.size());
		assertEquals(4, list.get(0).getId());
	}

	@Test
	@DisplayName("Test delete non-existent client")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void deleteNonExistentTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> addressService.findById(20));
		assertEquals("Endereço com ID 20 não encontrado", exception.getMessage());
	}

	@Test
	@DisplayName("Test add address")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })	
	void insertAddressTest() {
		var address = new Address(null, "Nilce Guarezi", "Centro", 2,new Client(6, null,null,null));
		addressService.insert(address);
		assertEquals(1, address.getId());
		assertEquals("Nilce Guarezi", address.getStreet());
		assertEquals("Centro", address.getNeighborhood());
		assertEquals(2, address.getNumber());
		assertEquals(6, address.getClient().getId_client());
	}

	@Test
	@DisplayName("Test change non-existent address")
	@Sql({ "classpath:/resources/sqls/client.sql" })
	@Sql({ "classpath:/resources/sqls/address.sql" })
	void updateAddressNonExistentTest() {
		var addressDto = new AddressDTO(20, "Avenida 1", "Centro", 200, 9);
		var address = new Address(addressDto);

		assertThrows(ObjectNotFound.class, () -> addressService.update(address));
	}

}
