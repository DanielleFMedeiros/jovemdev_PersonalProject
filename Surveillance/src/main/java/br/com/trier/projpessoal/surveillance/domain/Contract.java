package br.com.trier.projpessoal.surveillance.domain;

import java.time.LocalDate;

import br.com.trier.projpessoal.surveillance.domain.dto.ContractDTO;
import br.com.trier.projpessoal.surveillance.utils.DateUtils;
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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "contract")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date_initial")
	private LocalDate startDate;

	@Column(name = "date_final")
	private LocalDate endDate;

	@Column(name = "price")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;


    public Contract(ContractDTO dto) {
        this(dto.getId(), DateUtils.strToLocalDate(dto.getDate_initial()),
                DateUtils.strToLocalDate(dto.getDate_final()), dto.getPrice(),
                new Client(dto.getId_client(),null, dto.getCpf_client(), null),
                new Address(dto.getId_address(),null, null, null, null));
    }

    public ContractDTO toDto() {
        return new ContractDTO(id, DateUtils.localDateToStr(startDate),
                DateUtils.localDateToStr(endDate), price, client.getId_client(), client.getName(),
                client.getCpf(), address.getId(), address.getStreet());
    }

    public Contract(int id, LocalDate startDate, LocalDate endDate, double price, int clientId, int addressId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.client = new Client(clientId, null, null, null);
        this.address = new Address(addressId, null, null, null, null);
    }
}
