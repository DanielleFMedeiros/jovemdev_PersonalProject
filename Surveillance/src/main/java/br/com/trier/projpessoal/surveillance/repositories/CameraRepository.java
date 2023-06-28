package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Camera;

public interface CameraRepository extends JpaRepository<Camera, Integer> {

	Camera findByPrice(Double value);

	Optional<Camera> findById(Integer id);

	List<Camera> findByNameStartingWithIgnoreCase(String name);

}
