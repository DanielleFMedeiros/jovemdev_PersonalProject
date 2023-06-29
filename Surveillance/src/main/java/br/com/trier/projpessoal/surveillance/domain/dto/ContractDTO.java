package br.com.trier.projpessoal.surveillance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
	private Integer id;
	private String dateInitial;
	private String dateFinal;
	private Double price;
	private Integer id_client;
	private String name_client;
	private String cpf_client;
	private Integer id_address;
	private String street;
	
}
