package br.com.trier.projpessoal.surveillance.domain;

import java.time.ZonedDateTime;

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
    @Column
    private Integer id;

    @Column
    private ZonedDateTime dateInitial;

    @Column
    private ZonedDateTime dateFinal;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    public Contract(ContractDTO dto) {
        this(dto.getId(),
                DateUtils.strToZonedDateTime(dto.getDateInitial()),
                DateUtils.strToZonedDateTime(dto.getDateFinal()),
                dto.getPrice(),
                new Client(dto.getId_client(), dto.getName_client(), dto.getCpf_client(), null),
                new Address(dto.getId_address(), dto.getStreet(), null, null, null));
    }

    public ContractDTO toDto() {
        return new ContractDTO(id, DateUtils.zonedDateTimeToStr(dateInitial), DateUtils.zonedDateTimeToStr(dateFinal), price, client.getId_client(), client.getName(), client.getCpf(), address.getId(), address.getStreet());
    }

    public Contract(ContractDTO dto, Contract contract) {
        this(dto.getId(),
                DateUtils.strToZonedDateTime(dto.getDateInitial()),
                DateUtils.strToZonedDateTime(dto.getDateFinal()),
                dto.getPrice(),
                contract.getClient(),
                contract.getAddress());
    }
}
