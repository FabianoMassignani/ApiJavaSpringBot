package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_reserva")
public class Reserva extends BaseEntity {

    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "quantidade_pessoas")
    private int quantidadePessoas;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa cliente;

    // Um cliente (pessoa) pode fazer muitas reservas, mas cada reserva pertence a
    // um único cliente. Portanto, é um relacionamento Many-to-One, onde várias
    // reservas são feitas por um único cliente.

    @ManyToOne
    @JoinColumn(name = "passeio_id")
    private Passeio passeio;

    // Um passeio específico pode ter muitas reservas, mas cada reserva está
    // associada a um único passeio. Portanto, também é um relacionamento
    // Many-to-One, onde várias reservas podem ser feitas para o mesmo passeio.
}
