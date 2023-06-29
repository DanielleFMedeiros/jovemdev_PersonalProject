package br.com.trier.projpessoal.surveillance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Address;

@Service
public interface AddressService {
	Address insert(Address adress);

	List<Address> listAll();

	Address findById(Integer id);

	Address update(Address address);

	void delete(Integer id);

	List<Address> findByNeighborhood(String neighborhood);
	
	Optional<Address> findByNumber(Integer number);

	List<Address> findByStreetContainsIgnoreCase(String street);

}
