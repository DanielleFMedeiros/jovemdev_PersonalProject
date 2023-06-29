package br.com.trier.projpessoal.surveillance.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.projpessoal.surveillance.domain.Client;
import br.com.trier.projpessoal.surveillance.domain.dto.ClientDTO;
import br.com.trier.projpessoal.surveillance.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@PostMapping
	public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO) {
		Client createdClient = clientService.insert(new Client(clientDTO));
		return  ResponseEntity.ok(createdClient.toDto());
	}
	

	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> clients = clientService.listAll();
		return ResponseEntity.ok(clients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
		Client client = clientService.findById(id);
		return ResponseEntity.ok(client.toDto());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
		Client existingClient = clientService.findById(id);

		client.setId_client(id);
		Client updatedClient = clientService.update(client);
		return ResponseEntity.ok(updatedClient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
		Client existingClient = clientService.findById(id);
		clientService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Client>> getClientByNameContains(@PathVariable String name) {
		List<Client> clients = clientService.findByNameContainsIgnoreCase(name);

		return ResponseEntity.ok(clients);

	}

}
