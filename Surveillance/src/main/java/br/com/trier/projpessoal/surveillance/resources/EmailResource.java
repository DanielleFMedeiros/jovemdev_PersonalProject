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

import br.com.trier.projpessoal.surveillance.domain.Email;
import br.com.trier.projpessoal.surveillance.domain.dto.EmailDTO;
import br.com.trier.projpessoal.surveillance.services.EmailService;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {
	 @Autowired
	    private EmailService emailService;

	    @PostMapping
	    public ResponseEntity<EmailDTO> insertEmail(@RequestBody EmailDTO emailDTO) {
	        Email createdEmail = emailService.insert(new Email(emailDTO));
	        return ResponseEntity.ok(createdEmail.toDto());
	    }
	    
	    @GetMapping
		public ResponseEntity<List<Email>> getAllEmails() {
			List<Email> emails = emailService.listAll();
			return ResponseEntity.ok(emails);
		}

		@GetMapping("/{id}")
		public ResponseEntity<EmailDTO> getEmailById(@PathVariable Integer id) {
			Email email = emailService.findById(id);
			return ResponseEntity.ok(email.toDto());

		}

		@PutMapping("/{id}")
		public ResponseEntity<Email> updateEmail(@PathVariable Integer id, @RequestBody Email email) {
			Email existingEmail = emailService.findById(id);

			email.setId(id);
			Email updatedEmail = emailService.update(email);
			return ResponseEntity.ok(updatedEmail);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteEmail(@PathVariable Integer id) {
			Email existingEmail = emailService.findById(id);
			emailService.delete(id);
			return ResponseEntity.noContent().build();

		}

		@GetMapping("/email/{email}")
		public ResponseEntity<List<Email>> getEmailContainsIgnoreCase(@PathVariable String email) {
			List<Email> emails = emailService.findByEmailContainsIgnoreCase(email);

			return ResponseEntity.ok(emails);

		}
		

	}