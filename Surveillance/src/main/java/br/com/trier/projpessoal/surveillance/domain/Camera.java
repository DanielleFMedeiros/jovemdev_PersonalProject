package br.com.trier.projpessoal.surveillance.domain;


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

@Entity(name = "camera")

public class Camera extends Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	
	@Column
	private Integer id;
	
	@Column
	private String resolution;
	
	@ManyToOne
	private Client client;
}
