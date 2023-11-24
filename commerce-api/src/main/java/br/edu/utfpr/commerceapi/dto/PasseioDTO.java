package br.edu.utfpr.commerceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String destino;

    @NotBlank
    @Size(min = 2, max = 150)
    private String itinerario;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;
    
    @NotBlank(message = "O status não pode estar em branco")
    private boolean ativo;

    @NotBlank(message = "O valor não pode estar em branco")
    private double valor;

    @NotBlank(message = "A quantidade máxima de pessoas não pode estar em branco")
    private int quantidadeMaximaPessoas;
}
