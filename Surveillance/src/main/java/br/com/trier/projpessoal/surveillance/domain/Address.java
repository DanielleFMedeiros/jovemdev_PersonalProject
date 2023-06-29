package br.com.trier.projpessoal.surveillance.domain;

import br.com.trier.projpessoal.surveillance.domain.dto.AddressDTO;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "address")
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

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Address(AddressDTO dto) {
        this.id = dto.getId();
        this.street = dto.getStreet();
        this.neighborhood = dto.getNeighborhood();
        this.number = dto.getNumber();
        this.client = new Client(dto.getId_client(), null, null, null);
    }

    public AddressDTO toDto() {
        return new AddressDTO(this.id, this.street, this.neighborhood, this.number, client.getId_client());
    }

}
