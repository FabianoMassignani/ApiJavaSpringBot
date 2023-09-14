package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Pessoa;
import br.edu.utfpr.commerceapi.models.Reserva;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "O valor não pode estar em branco")
  private double valor;

  @NotBlank(message = "A data do pagamento não pode estar em branco")
  private LocalDateTime dataPagamento;

  @NotBlank(message = "O status não pode estar em branco")
  private String status;

  @NotBlank(message = "O método de pagamento não pode estar em branco")
  private String metodoPagamento;

  @NotBlank(message = "A reserva não pode estar em branco")
  private Reserva reserva;

  @NotBlank(message = "A pessoa não pode estar em branco")
  private Pessoa pessoa;
}
