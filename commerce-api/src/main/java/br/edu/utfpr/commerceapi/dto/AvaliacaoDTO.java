package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.models.Pessoa;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "O título não pode estar em branco")
  private String titulo;

  @NotBlank(message = "O classificação não pode estar em branco")
  private int classificacao;

  @NotBlank(message = "O data da avaliação não pode estar em branco")
  private LocalDate dataAvaliacao;

  private String comentario;

  @NotBlank(message = "O id do passeio não pode estar em branco")
  private Passeio passeio;

  @NotBlank(message = "O id do cliente não pode estar em branco")
  private Pessoa cliente;
}
