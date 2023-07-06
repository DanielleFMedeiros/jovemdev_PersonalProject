package br.com.trier.projpessoal.surveillance.services;

import java.util.List;

import br.com.trier.projpessoal.surveillance.domain.Email;

public interface EmailService {
	Email insert(Email email);

	List<Email> listAll();

	Email findById(Integer id);

	Email update(Email email);

	void delete(Integer id);

	List<Email> findByEmailContainsIgnoreCase(String email);
}