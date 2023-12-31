package br.com.trier.projpessoal.surveillance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "alarm")

public class Alarm extends Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter

	@Column
	private Integer id;

	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Client client;

}
