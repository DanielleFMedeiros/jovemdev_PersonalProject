package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByPrice(Double value);

	Optional<Product> findById(Integer id);

	List<Product> findByDescription(String description);

	List<Product> findByNameContainsIgnoreCase(String name);
}
