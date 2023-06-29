package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.trier.projpessoal.surveillance.domain.Address;
import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.repositories.AddressRepository;
import br.com.trier.projpessoal.surveillance.services.AddressService;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository repository;

	@Configuration
	public class AppConfig {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Override
	public Address insert(Address address) {
		return repository.save(address);
	}

	@Override
	public List<Address> listAll() {
		List<Address> list = repository.findAll();
		if (list.size() == 0) {
			throw new ObjectNotFound("Nenhum endereço encontrado");
		}
		return repository.findAll();
	}

	@Override
	public Address findById(Integer id) {
		Optional<Address> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFound("Endereço com ID %s não encontrado".formatted(id)));

	}

	@Override
	public Address update(Address address) {
		Optional<Address> existingClient = repository.findById(address.getId());
		if (existingClient.isEmpty()) {
			throw new ObjectNotFound("Endereço com ID %s não encontrado".formatted(address.getId()));
		}
		return repository.save(address);
	}

	@Override
	public void delete(Integer id) {
		Address address = findById(id);
		repository.delete(address);
	}

	@Override
	public List<Address> findByStreetContainsIgnoreCase(String street) {
		List<Address> address = repository.findByStreetContainsIgnoreCase(street);

		if (address.isEmpty()) {
			throw new ObjectNotFound("Nenhum endereço encontrado com nome contendo: " + street);
		}

		return address;
	}

	@Override
	public List<Address> findByNeighborhood(String neighborhood) {
		List<Address> address = repository.findByNeighborhood(neighborhood);
		if (address.isEmpty()) {
			throw new ObjectNotFound("Bairro %s não encontrado".formatted(neighborhood));
		}
		return address;
	}

	@Override
	public Optional<Address> findByNumber(Integer number) {
		Optional<Address> address = repository.findByNumber(number);
		if (address.isEmpty()) {
			throw new ObjectNotFound("Número %s não encontrado".formatted(number));
		}
		return address;

	}

}
