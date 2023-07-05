package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Product;
import br.com.trier.projpessoal.surveillance.repositories.ProductRepository;
import br.com.trier.projpessoal.surveillance.services.ProductService;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Product insert(Product product) {
		return repository.save(product);
	}

	@Override
	public List<Product> listAll() {
		return repository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFound("Produto com ID %s não encontrado".formatted(id)));
	}

	@Override
	public Product update(Product product) {
		Optional<Product> existingProduto = repository.findById(product.getId());
		if (existingProduto.isEmpty()) {
			throw new ObjectNotFound("Produto com ID %s não encontrado".formatted(product.getId()));
		}
		return repository.save(product);
	}

	@Override
	public void delete(Integer id) {
		Product product = findById(id);
		repository.delete(product);

	}

	@Override
	public List<Product> findByNameContainsIgnoreCase(String name) {
		List<Product> products = repository.findByNameContainsIgnoreCase(name);

		if (products.isEmpty()) {
			throw new ObjectNotFound("Nenhum produto encontrado com nome contendo: " + name);
		}

		return products;
	}

	@Override
	public List<Product> findByPrice(Double price) {
		List<Product> produtos = repository.findByPrice(price);

		if (produtos.isEmpty()) {
			throw new ObjectNotFound("Nenhum preço de %s encontrado: " + price);
		}

		return produtos;
	}

	@Override
	public List<Product> findByDescriptionContainsIgnoreCase(String description) {
		List<Product> products = repository.findByDescription(description);

		if (products.isEmpty()) {
			throw new ObjectNotFound("Nenhum tipo de produto encontrado com %s: " + description);
		}

		return products;
	}

}
