package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;

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
public class Reserva  extends BaseEntity{
    
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    @Column(name = "quantidade_pessoas", nullable = false)
    private int quantidadePessoas;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "data")
    private LocalDateTime data;

}
