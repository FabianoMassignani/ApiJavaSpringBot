package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_passeio")
public class Passeio extends BaseEntity {

    @Column(name = "destino", length = 150)
    private String destino;

    @Column(name = "itinerario", length = 150)
    private String itinerario;

    @Column(name = "preco")
    private double preco;

    // Relacionamento One-to-Many com Reserva: Um passeio pode ter muitas reservas.
    @OneToMany(mappedBy = "passeio")
    private List<Reserva> reservas = new ArrayList<>();

    // Relacionamento One-to-Many com Avaliacao: Um passeio pode ter muitas
    // avaliações.
    @OneToMany(mappedBy = "passeio")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}