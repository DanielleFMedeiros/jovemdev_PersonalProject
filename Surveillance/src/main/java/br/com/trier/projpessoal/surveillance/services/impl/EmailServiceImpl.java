package br.com.trier.projpessoal.surveillance.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.projpessoal.surveillance.domain.Email;
import br.com.trier.projpessoal.surveillance.repositories.EmailRepository;
import br.com.trier.projpessoal.surveillance.services.EmailService;
import br.com.trier.projpessoal.surveillance.services.exceptions.BreachOfIntegrity;
import br.com.trier.projpessoal.surveillance.services.exceptions.ObjectNotFound;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRepository repository;

	@Override
	public Email insert(Email email) {

		if (email.getEmail() == null || email.getEmail().isEmpty()) {
			throw new BreachOfIntegrity("O email é obrigatório");
		}
		if (email.getClient() == null) {
			throw new BreachOfIntegrity("O cliente é obrigatório");
		}

		return repository.save(email);
	}

	@Override
	public List<Email> listAll() {
		List<Email> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ObjectNotFound("Nenhum endereço de email encontrado");
		}
		return list;
	}

	@Override
	public Email findById(Integer id) {
		Optional<Email> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFound("Email com ID %s não encontrado".formatted(id)));

	}

	@Override
	public Email update(Email email) {
		Optional<Email> existingEmail = repository.findById(email.getId());
		if (existingEmail.isEmpty()) {
			throw new ObjectNotFound("Endereço de email com ID %s não encontrado".formatted(email.getId()));
		}
		return repository.save(email);
	}

	@Override
	public void delete(Integer id) {
		Email email = findById(id);
		repository.delete(email);
	}

	@Override
	public List<Email> findByEmailContainsIgnoreCase(String email) {
		List<Email> emais = repository.findByEmailContainsIgnoreCase(email);

		if (email.isEmpty()) {
			throw new ObjectNotFound("Nenhum endereço de email encontrado com nome contendo: " + email);
		}

		return emais;
	}

}