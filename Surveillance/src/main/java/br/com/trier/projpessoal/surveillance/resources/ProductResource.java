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

import br.com.trier.projpessoal.surveillance.domain.Product;
import br.com.trier.projpessoal.surveillance.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product insertProduct(@RequestBody Product product) {
        return productService.insert(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
    	productService.delete(id);
    }
    
    @GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> getProductByNameContains(@PathVariable String name) {
		List<Product> products = productService.findByNameContainsIgnoreCase(name);

		return ResponseEntity.ok(products);

	}
    
    @GetMapping("/description/{description}")
	public ResponseEntity<List<Product>> getProductByDescriptionContains(@PathVariable String description) {
		List<Product> products = productService.findByDescriptionContainsIgnoreCase(description);

		return ResponseEntity.ok(products);

	}
    
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Product>> getProductByPrice(@PathVariable Double price) {
        List<Product> products = productService.findByPrice(price);
        return ResponseEntity.ok(products);
    }
}