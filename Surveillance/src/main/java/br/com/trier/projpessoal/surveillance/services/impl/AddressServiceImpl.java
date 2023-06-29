package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Address;
import br.com.trier.projpessoal.surveillance.repositories.AddressRepository;
import br.com.trier.projpessoal.surveillance.services.AddressService;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository repository;
	
	@Override
	public Address insert(Address address) {
	    if (address.getStreet() == null || address.getStreet().isEmpty()) {
	        throw new BreachOfIntegrity("A rua é obrigatória");
	    }
	    if (address.getNeighborhood() == null || address.getNeighborhood().isEmpty()) {
	        throw new BreachOfIntegrity("O bairro é obrigatório");
	    }
	    if (address.getNumber() <= 0) {
	        throw new BreachOfIntegrity("O número deve ser maior que zero");
	    }
	    if (address.getClient() == null) {
	        throw new BreachOfIntegrity("O cliente é obrigatório");
	    }

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
