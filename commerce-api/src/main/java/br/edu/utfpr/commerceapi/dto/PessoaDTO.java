package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Reserva;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
public class PessoaDTO {

  @NotBlank(message = "O nome não pode estar em branco")
  private String nome;

  @NotBlank(message = "O email não pode estar em branco")
  private String email;

  @NotBlank(message = "A senha não pode estar em branco")
  private String password;

  private LocalDateTime dataNascimento;
  private String cpf;
  private String cnpj;
  private String telefone;
  private Boolean isPessoaFisica;

  private List<Reserva> reservas;
}
