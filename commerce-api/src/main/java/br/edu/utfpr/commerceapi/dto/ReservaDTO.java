package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Passeio;
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
public class ReservaDTO {

  private LocalDate dataReserva;
  private int quantidadePessoas;
  private String observacao;
  private PessoaDTO cliente;
  private Passeio passeio;

}
