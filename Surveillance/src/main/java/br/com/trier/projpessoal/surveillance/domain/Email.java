package br.com.trier.projpessoal.surveillance.domain;
import br.com.trier.projpessoal.surveillance.domain.dto.EmailDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "email")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	
	@Column
	private Integer id;
	@Column
	private String email;
	
	@ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Email(EmailDTO dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.client = new Client(dto.getId_client(), null, null, null);
    }

    public EmailDTO toDto() {
        return new EmailDTO(this.id, this.email, client.getId_client());
    }
}