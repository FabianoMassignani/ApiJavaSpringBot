package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.utfpr.commerceapi.models.Reserva;

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
    private String nome;
    private String email;
    private LocalDateTime nascimento;
    private String cpf;
    private String cnpj;
    private String password;
    private String telefone;
    private Boolean isPessoaFisica; // Pode ser "Cliente" ou "Agência De Viagens"
    private List<Reserva> reservas;
}
