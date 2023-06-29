package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.repositories.ClientRepository;
import br.com.trier.projpessoal.surveillance.services.ClientService;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	@Configuration
	public class AppConfig {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	private void validateByCpf(Client obj) {
		Optional<Client> clientRequired = repository.findByCpf(obj.getCpf());
		if (clientRequired.isPresent()) {
			Client client = clientRequired.get();
			if (client.getId() != obj.getId()) {
				throw new BreachOfIntegrity("CPF já foi registrado: %s".formatted(obj.getCpf()));
			}
		}
	}

	@Override
	public Client insert(Client client) {
		validateByCpf(client);
		return repository.save(client);
	}

	@Override
	public List<Client> listAll() {
		List<Client> list = repository.findAll();
		if (list.size() == 0) {
			throw new ObjectNotFound("Nenhum cliente encontrado");
		}
		return repository.findAll();
	}

	@Override
	public Client findById(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFound("Cliente com ID %s não encontrado".formatted(id)));
	}

	@Override
	public Client update(Client client) {
		Optional<Client> existingClient = repository.findById(client.getId());
		if (existingClient.isEmpty()) {
			throw new ObjectNotFound("Cliente com ID %s não encontrado".formatted(client.getId()));
		}

		Client foundClient = existingClient.get();
		if (!foundClient.getCpf().equals(client.getCpf())) {
			validateByCpf(client);
		}

		return repository.save(client);
	}

	@Override
	public void delete(Integer id) {
		Client client = findById(id);
		repository.delete(client);
	}

	@Override
	public List<Client> findByNameContainsIgnoreCase(String name) {
		List<Client> clients = repository.findByNameContainsIgnoreCase(name);

		if (clients.isEmpty()) {
			throw new ObjectNotFound("Nenhum cliente encontrado com nome contendo: " + name);
		}

		return clients;
	}

	@Override
	public Optional<Client> findByCpf(String cpf) {
		Optional<Client> client = repository.findByCpf(cpf);
		if (client.isEmpty()) {
			throw new ObjectNotFound("Cliente com CPF %s não encontrado".formatted(cpf));
		}
		return client;
	}

}
