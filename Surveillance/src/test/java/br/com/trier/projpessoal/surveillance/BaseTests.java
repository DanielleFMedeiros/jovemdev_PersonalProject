package br.com.trier.projpessoal.surveillance;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import br.com.trier.projpessoal.surveillance.services.ClientService;
import br.com.trier.projpessoal.surveillance.services.impl.ClientServiceImpl;

@TestConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class BaseTests {

	@Bean
	public ClientService clientService() {
		return new ClientServiceImpl();
	}

//	@Bean
//	public PaisService paisService() {
//		return new PaisServiceImpl();
//	}
//	
//	@Bean
//	public EquipeService equipeService() {
//		return new EquipeServiceImpl();
//	}
//	
//	@Bean
//	public CampeonatoService campeonatoService() {
//		return new CampeonatoServiceImpl();
//	}
//	
//	@Bean
//	public PilotoService pilotoService() {
//		return new PilotoServiceImpl();
//	}
//	
//	@Bean
//	public PistaService pistaService() {
//		return new PistaServiceImpl();
//	}
//	
//	@Bean
//	public CorridaService corridaService() {
//		return new CorridaServiceImpl();
//	}
//	
//	@Bean
//	public PilotoCorridaService pilotoCorridaService() {
//		return new PilotoCorridaServiceImpl();
//	}

}
