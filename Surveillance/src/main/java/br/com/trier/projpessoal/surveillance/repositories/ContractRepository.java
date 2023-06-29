package br.com.trier.projpessoal.surveillance.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    List<Contract> findByDateInitial(ZonedDateTime dateInitial);

    List<Contract> findByDateFinal(ZonedDateTime dateFinal);

	List<Contract> findByDataBetween(ZonedDateTime dataInicial, ZonedDateTime dataFinal);

    List<Contract> findByPrice(Double price);
    
    Optional<Contract> findByClient_Cpf(String cpf);
}
