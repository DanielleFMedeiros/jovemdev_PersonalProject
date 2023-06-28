package br.com.trier.projpessoal.surveillance.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
	private Integer id;
	private String name;
	private String cpf;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

}
