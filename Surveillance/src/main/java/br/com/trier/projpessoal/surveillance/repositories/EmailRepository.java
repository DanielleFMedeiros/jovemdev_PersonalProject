package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{

	Optional<Email> findById(Integer id);

	List<Email> findByEmailContainsIgnoreCase(String email);
	
	Optional<Email> findByIdClient(Client client);
}