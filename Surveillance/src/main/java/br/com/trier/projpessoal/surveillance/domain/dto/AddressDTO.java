package br.com.trier.projpessoal.surveillance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private Integer id;
	private String street;
	private String neighborhood;
	private Integer number;
	private Integer id_client;
}
