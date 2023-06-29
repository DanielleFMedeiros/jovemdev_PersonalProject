package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByNumber(Integer number);

	List<Address> findByNeighborhood(String neighborhood);

	Optional<Address> findById(Integer id);

	List<Address> findByStreetContainsIgnoreCase(String street);


}
