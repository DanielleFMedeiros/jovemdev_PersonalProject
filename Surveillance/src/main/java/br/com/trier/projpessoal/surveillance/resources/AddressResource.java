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

import br.com.trier.projpessoal.surveillance.domain.Address;
import br.com.trier.projpessoal.surveillance.domain.dto.AddressDTO;
import br.com.trier.projpessoal.surveillance.services.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {
	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<AddressDTO> insertAddress(@RequestBody AddressDTO addressDTO) {
		Address createdAddress = addressService.insert(new Address(addressDTO));
		return  ResponseEntity.ok(createdAddress.toDto());
	}
	

	@GetMapping
	public ResponseEntity<List<Address>> getAllAddresss() {
		List<Address> addresss = addressService.listAll();
		return ResponseEntity.ok(addresss);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer id) {
		Address address = addressService.findById(id);
		return ResponseEntity.ok(address.toDto());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
		Address existingAddress = addressService.findById(id);

		address.setId(id);
		Address updatedAddress = addressService.update(address);
		return ResponseEntity.ok(updatedAddress);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
		Address existingAddress = addressService.findById(id);
		addressService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/street/{street}")
	public ResponseEntity<List<Address>> getAddressByNameContains(@PathVariable String street) {
		List<Address> addresss = addressService.findByStreetContainsIgnoreCase(street);

		return ResponseEntity.ok(addresss);

	}
	
	@GetMapping("/neighborhood/{neighborhood}")
	public ResponseEntity<List<Address>> getAddressByNeighborhoodContains(@PathVariable String neighborhood) {
		List<Address> addresss = addressService.findByNeighborhood(neighborhood);

		return ResponseEntity.ok(addresss);

	}

}
