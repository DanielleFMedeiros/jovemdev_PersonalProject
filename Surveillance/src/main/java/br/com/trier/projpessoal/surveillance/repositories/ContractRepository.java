package br.com.trier.projpessoal.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
/*
 * Encontrar contrato por:
- Datas entre
- Data de inicio
- Data de fim
- valor do contrato 
- puxar contrato de clientes.
- listar todos contratos 
- por id.*/
}
