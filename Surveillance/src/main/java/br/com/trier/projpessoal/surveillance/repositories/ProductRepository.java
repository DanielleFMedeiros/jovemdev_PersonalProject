package br.com.trier.projpessoal.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Client;

public interface ProductRepository extends JpaRepository<Client, Integer>{
/*
 * Encontrar produtos por:
- Nome com ignore case
- tipo de produto, camera ou alarme
- valor do produto
- listar todos os produtos
- por id.
 */
}
