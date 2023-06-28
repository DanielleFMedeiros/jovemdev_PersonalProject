package br.com.trier.projpessoal.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Telephone;

public interface TelephoneRepository extends JpaRepository<Telephone, Integer>{
/*
 - nome de clientes que possuem tal telefone,
- por id,
- listar todos os telefones,
- telefone que começa por.
 */
}
