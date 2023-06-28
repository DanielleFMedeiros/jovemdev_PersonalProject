package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client findByCpf(String cpf);

	Optional<Client> findByEmail(String email);

	List<Client> findByNameStartingWithIgnoreCase(String name);
	
	Optional<Client> findById(Integer id);

}
