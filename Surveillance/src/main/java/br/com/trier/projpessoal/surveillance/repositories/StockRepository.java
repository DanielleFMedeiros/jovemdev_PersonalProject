package br.com.trier.projpessoal.surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{
/*
 * Encontrar estoque de produtos por:
- Produtos que estão com menos de 5 em estoque,
- Quais produtos possuem maior em estoque,
- Quais produtos possuem menor em estoque,
- listar todos os estoques
- por id.
 */
}
