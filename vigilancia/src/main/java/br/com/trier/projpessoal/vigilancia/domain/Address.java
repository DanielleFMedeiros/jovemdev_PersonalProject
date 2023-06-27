package br.com.trier.projpessoal.vigilancia.domain;

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
@Entity(name = "adress")

public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	
	@Column
	private Integer id;
	@Column
	private String street;
	@Column
	private String neighborhood;
	@Column
	private Integer number;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String zipCode;
	
	@ManyToOne
	private Client client;
}
