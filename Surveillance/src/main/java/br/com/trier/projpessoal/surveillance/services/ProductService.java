package br.com.trier.projpessoal.surveillance.services;

import java.util.List;

import br.com.trier.projpessoal.surveillance.domain.Product;

public interface ProductService {
	Product insert(Product product);

	List<Product> listAll();

	Product findById(Integer id);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findByNameContainsIgnoreCase(String name);

	List<Product> findByPrice(Double price);

	List<Product> findByDescriptionContainsIgnoreCase(String description);
}
