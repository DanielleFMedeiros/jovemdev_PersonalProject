package br.com.trier.projpessoal.surveillance.services.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Contract;
import br.com.trier.projpessoal.surveillance.repositories.ContractRepository;
import br.com.trier.projpessoal.surveillance.services.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

	private ContractRepository contractRepository;

	@Autowired
	public ContractServiceImpl(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

	@Override
	public Contract insert(Contract contract) {
		return contractRepository.save(contract);
	}

	@Override
	public List<Contract> listAll() {
		return contractRepository.findAll();
	}

	@Override
	public Contract findById(Integer id) {
		Optional<Contract> optionalContract = contractRepository.findById(id);
		return optionalContract.orElse(null);
	}

	@Override
	public Contract update(Contract contract) {
		return contractRepository.save(contract);
	}

	@Override
	public void delete(Integer id) {
		contractRepository.deleteById(id);
	}

	@Override
	public List<Contract> findByPrice(Double price) {
		return contractRepository.findByPrice(price);
	}

	@Override
	public List<Contract> findByDateInitial(ZonedDateTime dateInitial) {
		return contractRepository.findByDateInitial(dateInitial);
	}

	@Override
	public List<Contract> findByDataBetween(ZonedDateTime dataInicial, ZonedDateTime dataFinal) {
		return contractRepository.findByDataBetween(dataInicial, dataFinal);
	}

	@Override
	public List<Contract> findByDateFinal(ZonedDateTime dateFinal) {

		return contractRepository.findByDateFinal(dateFinal);
	}

	@Override
	public Optional<Contract> findByCpf(String cpf) {
        return contractRepository.findByClient_Cpf(cpf);

	}

}
