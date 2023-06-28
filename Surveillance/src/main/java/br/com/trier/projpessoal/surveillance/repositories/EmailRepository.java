package br.com.trier.projpessoal.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{
/*
 * Encontrar clientes by email:
- Email que começa por 
- id do cliente daquele email.
- listar todos emails
- por id.
 */
}
