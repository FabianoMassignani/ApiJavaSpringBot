package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Avaliacao;
import br.edu.utfpr.commerceapi.models.Reserva;
import java.util.ArrayList;
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

  private String destino;
  private String itinerario;
  private double preco;
  private int quantidadeMaximaPessoas;
  private String observacao;
  private String dataHoraSaida;
  private String dataHoraRetorno;
  private List<Reserva> reservas = new ArrayList<>();
  private List<Avaliacao> avaliacoes = new ArrayList<>();
}
