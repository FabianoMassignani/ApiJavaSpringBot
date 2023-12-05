package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDateTime;

import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.models.Pessoa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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

public class ReservaDTO{
    
    @NotBlank(message = "O id do pacote não pode estar em branco")
    private Pacote pacote;

    @NotBlank(message = "O id do cliente não pode estar em branco")
    private Pessoa pessoa;

    @NotBlank(message = "O data da reserva não pode estar em branco")
    @FutureOrPresent
    private LocalDateTime data;

    @NotBlank(message = "O status da reserva não pode estar em branco")
    private boolean reservaAceita;
}