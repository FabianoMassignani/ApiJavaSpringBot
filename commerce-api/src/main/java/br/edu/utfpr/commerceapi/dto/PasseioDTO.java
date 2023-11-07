package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Reserva;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
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
public class PasseioDTO {

  @NotBlank(message = "O nome não pode estar em branco")
  private String nome;

  @NotBlank(message = "A descrição não pode estar em branco")
  private String descricao;

  private String imagem;

  @NotBlank(message = "O status não pode estar em branco")
  private boolean ativo;

  @NotBlank(message = "O valor não pode estar em branco")
  private double valor;

  @NotBlank(message = "A origem não pode estar em branco")
  private String origem;

  @NotBlank(message = "O destino não pode estar em branco")
  private String destino;

  private String itinerario;

  @NotBlank(message = "A data e hora de saída não pode estar em branco")
  private String dataHoraSaida;

  @NotBlank(message = "A data e hora de retorno não pode estar em branco")
  private String dataHoraRetorno;

  @NotBlank(message = "A quantidade máxima de pessoas não pode estar em branco")
  private int quantidadeMaximaPessoas;

  private String observacao;

  private List<Reserva> reservas;
  // private List<Avaliacao> avaliacoes;
}
