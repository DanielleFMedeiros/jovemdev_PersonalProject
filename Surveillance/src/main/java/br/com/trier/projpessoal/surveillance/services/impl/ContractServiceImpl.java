package br.com.trier.projpessoal.surveillance.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Contract;
import br.com.trier.projpessoal.surveillance.repositories.ContractRepository;
import br.com.trier.projpessoal.surveillance.services.ContractService;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract insert(Contract contract) {
        if (contract.getStartDate() == null) {
            throw new BreachOfIntegrity("A data inicial é obrigatória");
        }
        if (contract.getEndDate() == null) {
            throw new BreachOfIntegrity("A data final é obrigatória");
        }
        if (contract.getPrice() <= 0) {
            throw new BreachOfIntegrity("O valor deve ser maior que zero");
        }
        if (contract.getClient() == null) {
            throw new BreachOfIntegrity("O cliente é obrigatório");
        }

        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> listAll() {
        List<Contract> list = contractRepository.findAll();
        if (list.size() == 0) {
            throw new ObjectNotFound("Nenhum contrato encontrado");
        }
        return contractRepository.findAll();
    }

    @Override
    public Contract findById(Integer id) {
        Optional<Contract> obj = contractRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFound("Contrato com ID %s não encontrado".formatted(id)));
    }

    @Override
    public Contract update(Contract contract) {
        Optional<Contract> existingContract = contractRepository.findById(contract.getId());
        if (existingContract.isEmpty()) {
            throw new ObjectNotFound("Endereço com ID %s não encontrado".formatted(contract.getId()));
        }
        return contractRepository.save(contract);
    }

    @Override
    public void delete(Integer id) {
        Contract contract = findById(id);
        contractRepository.delete(contract);
    }

    @Override
    public List<Contract> findByPrice(Double price) {
        List<Contract> contract = contractRepository.findByPrice(price);

        if (contract.isEmpty()) {
            throw new ObjectNotFound("Nenhum contrato encontrado com esse preço: " + price);
        }

        return contract;
    }

    @Override
    public List<Contract> findByStartDate(LocalDate dateInitial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (contractRepository.findByStartDate(dateInitial).size() == 0) {
            throw new ObjectNotFound("Não há contratos na data: " + formatter.format(dateInitial));
        }
        return contractRepository.findByStartDate(dateInitial);
    }


    @Override
    public List<Contract> findByEndDate (LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (contractRepository.findByEndDate(endDate).size() == 0) {
            throw new ObjectNotFound("Não há contratos na data: " + formatter.format(endDate));
        }
        return contractRepository.findByEndDate(endDate);
    }

    @Override
    public Optional<Contract> findByCpf(String cpf) {
        Optional<Contract> client = contractRepository.findByClient_Cpf(cpf);
        if (client.isEmpty()) {
            throw new ObjectNotFound("Cliente com CPF %s não encontrado".formatted(cpf));
        }
        return client;
    }
}
