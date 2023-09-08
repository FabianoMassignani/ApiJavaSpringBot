package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Reserva;
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
public class PagamentoDTO {

  private double valor;
  private LocalDateTime dataPagamento;
  private String status;
  private String metodoPagamento;
  private Reserva reserva;
}
