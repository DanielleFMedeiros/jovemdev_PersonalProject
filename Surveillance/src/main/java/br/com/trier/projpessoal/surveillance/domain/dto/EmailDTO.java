package br.com.trier.projpessoal.surveillance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
	private Integer id;
	private String email;
	private Integer id_client;
}