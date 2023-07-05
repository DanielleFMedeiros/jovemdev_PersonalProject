package br.com.trier.projpessoal.surveillance.services;

import java.util.List;
import java.util.Optional;

import br.com.trier.projpessoal.surveillance.domain.Client;

public interface ClientService {

	Client insert(Client client);

	List<Client> listAll();

	Client findById(Integer id);

	Client update(Client client);

	void delete(Integer id);

	List<Client> findByNameContainsIgnoreCase(String name);

	Optional<Client> findByCpf(String cpf);

}
