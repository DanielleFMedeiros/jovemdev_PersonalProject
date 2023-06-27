package br.com.trier.projpessoal.vigilancia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.vigilancia.domain.Alarm;


public interface AlarmRepository extends JpaRepository<Alarm, Integer>{
	/*
	 * Encontrar alarme por:
	  - nome começando por
	  - tipo de alarme startingwithignorecase
	  - preco do alarme entre
	  - preco do alarme
	  - listar todos os alarmes em ordem A-Z.
	 */
}
