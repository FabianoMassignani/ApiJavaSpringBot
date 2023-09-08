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
public class AvaliacaoDTO {

  private String titulo;
  private int classificacao;
  private String comentario;
  private LocalDate dataAvaliacao;
  private Passeio passeio;
  private PessoaDTO cliente;
}
