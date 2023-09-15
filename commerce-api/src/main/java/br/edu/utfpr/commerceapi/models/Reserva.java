package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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

  @Column(name = "data_reserva", nullable = false)
  private LocalDate dataReserva;

  @Column(name = "quantidade_pessoas", nullable = false)
  private int quantidadePessoas;

  @Column(name = "observacao")
  private String observacao;

  @ManyToOne
  @JoinColumn(name = "pessoa_id")
  private Pessoa cliente;

  @ManyToOne
  @JoinColumn(name = "passeio_id")
  private Passeio passeio;
}
