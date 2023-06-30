package br.com.trier.projpessoal.surveillance.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    List<Contract> findByDateInitial(LocalDate dateInitial);

    List<Contract> findByDateFinal(LocalDate dateFinal);

	List<Contract> findByDataBetween(LocalDate dateInicial, LocalDate dateFinal);

    List<Contract> findByPrice(Double price);
    
    Optional<Contract> findByClient_Cpf(String cpf);
}
