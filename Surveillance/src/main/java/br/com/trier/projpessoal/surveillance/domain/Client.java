package br.com.trier.projpessoal.surveillance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "client")

public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter

	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String cpf;

}
