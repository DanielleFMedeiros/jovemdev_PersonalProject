package br.com.trier.projpessoal.vigilancia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "alarm")

public class Alarm extends Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter

	@Column
	private Integer id;
	@Column
	private String resolution;

	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Client client;

}
