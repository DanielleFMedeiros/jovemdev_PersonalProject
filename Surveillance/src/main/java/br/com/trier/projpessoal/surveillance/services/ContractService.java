package br.com.trier.projpessoal.surveillance.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.trier.projpessoal.surveillance.domain.Contract;

public interface ContractService {

	Contract insert(Contract contract);

	List<Contract> listAll();

	Contract findById(Integer id);

	Contract update(Contract contract);

	void delete(Integer id);

	Optional<Contract> findByCpf(String cpf);

	List<Contract> findByPrice(Double price);

	List<Contract> findByStartDate(LocalDate startDate);

	List<Contract> findByEndDate(LocalDate endDate);

}