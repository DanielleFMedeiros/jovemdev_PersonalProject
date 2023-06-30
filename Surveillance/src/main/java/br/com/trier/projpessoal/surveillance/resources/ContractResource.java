package br.com.trier.projpessoal.surveillance.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.projpessoal.surveillance.domain.Contract;
import br.com.trier.projpessoal.surveillance.domain.dto.ContractDTO;
import br.com.trier.projpessoal.surveillance.services.ContractService;
import br.com.trier.projpessoal.surveillance.utils.DateUtils;

@RestController
@RequestMapping(value = "/contracts")
public class ContractResource {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractDTO> insertContract(@RequestBody ContractDTO contractDTO) {
        Contract createdContract = contractService.insert(new Contract(contractDTO));
        return ResponseEntity.ok(createdContract.toDto());
    }

    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        List<Contract> contracts = contractService.listAll();
        return ResponseEntity.ok(contracts.stream().map(Contract::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable Integer id) {
        Contract contract = contractService.findById(id);
        return ResponseEntity.ok(contract.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Integer id, @RequestBody ContractDTO contractDTO) {
        Contract existingContract = contractService.findById(id);

        Contract contract = new Contract(contractDTO, existingContract);
        contract.setId(id);

        Contract updatedContract = contractService.update(contract);
        return ResponseEntity.ok(updatedContract.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<ContractDTO>> getContractsByPrice(@PathVariable Double price) {
        List<Contract> contracts = contractService.findByPrice(price);
        return ResponseEntity.ok(contracts.stream().map(Contract::toDto).toList());
    }

    @GetMapping("/dateInitial/{dateInitial}")
    public ResponseEntity<List<ContractDTO>> getContractsByDateInitial(@PathVariable String dateInitial) {
        List<Contract> contracts = contractService.findByDateInitial(DateUtils.strToLocalDate(dateInitial));
        return ResponseEntity.ok(contracts.stream().map(Contract::toDto).toList());
    }

    @GetMapping("/dateBetween/{startDate}/{endDate}")
    public ResponseEntity<List<ContractDTO>> getContractsByDateBetween(@PathVariable String dateInitial, @PathVariable String dateFinal) {
        List<Contract> contracts = contractService.findByDateBetween(DateUtils.strToLocalDate(dateInitial), DateUtils.strToLocalDateFinal(dateFinal));
        return ResponseEntity.ok(contracts.stream().map(Contract::toDto).toList());
    }
}
