package br.com.trier.projpessoal.vigilancia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.vigilancia.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	/*
	 * - Encontrar cliente por: - nome começando por com ignore case, - encontrar
	 * por cpf do cliente, - encontrar por email do cliente. - listar todos os
	 * clientes em ordem A-Z
	 * 
	 */

	Client findByCpf(String cpf);

	Optional<Client> findByEmail(String email);

	List<Client> findByNameStartingWithIgnoreCase(String name);

}
