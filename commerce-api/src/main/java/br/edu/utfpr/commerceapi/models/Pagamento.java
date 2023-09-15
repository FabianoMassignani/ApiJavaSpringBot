package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "tb_pagamento")
public class Pagamento extends BaseEntity {

  @Column(name = "valor", nullable = false)
  private double valor;

  @Column(name = "data_pagamento", nullable = false)
  private LocalDateTime dataPagamento;

  @Column(name = "status", length = 150, nullable = false)
  private String status; // Pode ser "Pago" ou "Pendente"

  @Column(name = "metodo_pagamento", length = 150, nullable = false)
  private String metodoPagamento; // Adicionado campo para armazenar o método de pagamento utilizado

  @ManyToOne
  @JoinColumn(name = "reserva_id")
  private Reserva reserva;
}
