package br.edu.utfpr.commerceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 2, max = 150)
    private String nome;

    @NotBlank(message = "A origem não pode estar em branco")
    @Size(min = 2, max = 50)
    private String origem;

    @NotBlank(message = "O destino não pode estar em branco")
    @Size(min = 2, max = 50)
    private String destino;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;

    @NotNull(message = "O status não pode estar em branco")
    private Boolean ativo;

    @NotNull(message = "O valor não pode estar em branco")
    private Double valor;

    @NotNull(message = "A quantidade máxima de pessoas não pode estar em branco")
    private Integer quantidadeMaximaPessoas;
}
