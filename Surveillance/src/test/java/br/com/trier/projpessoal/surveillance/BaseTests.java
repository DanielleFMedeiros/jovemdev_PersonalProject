package br.com.trier.projpessoal.surveillance;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import br.com.trier.projpessoal.surveillance.services.AddressService;
import br.com.trier.projpessoal.surveillance.services.ClientService;
import br.com.trier.projpessoal.surveillance.services.ContractService;
import br.com.trier.projpessoal.surveillance.services.impl.AddressServiceImpl;
import br.com.trier.projpessoal.surveillance.services.impl.ClientServiceImpl;
import br.com.trier.projpessoal.surveillance.services.impl.ContractServiceImpl;

@TestConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class BaseTests {

	@Bean
	public ClientService clientService() {
		return new ClientServiceImpl();
	}

	@Bean
	public AddressService addressService() {
		return new AddressServiceImpl();
	}
	
	@Bean
	public ContractService contractService() {
		return new ContractServiceImpl();
	}


}
