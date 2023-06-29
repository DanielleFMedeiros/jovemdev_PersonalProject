package br.com.trier.projpessoal.surveillance.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.projpessoal.surveillance.services.AddressService;
import br.com.trier.projpessoal.surveillance.services.ClientService;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {
	@Autowired
	private AddressService addressService;
	
	
}
