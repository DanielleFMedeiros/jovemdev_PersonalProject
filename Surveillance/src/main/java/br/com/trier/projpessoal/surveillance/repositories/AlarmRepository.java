package br.com.trier.projpessoal.surveillance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.projpessoal.surveillance.domain.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

	Alarm findByValue(Double value);

	Optional<Alarm> findById(Integer id);

	List<Alarm> findByNameStartingWithIgnoreCase(String name);
}
