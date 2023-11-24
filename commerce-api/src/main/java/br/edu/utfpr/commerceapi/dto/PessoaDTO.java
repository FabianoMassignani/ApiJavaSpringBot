package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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

public class PessoaDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 2, max = 150)
    private String nome;
    
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    @Size(min = 10, max = 150, message = "O e-mail deve ter entre 10 e 150 caractéres")
    private String email;
    
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 8, max = 20)
    private String telefone;

    @PastOrPresent
    private LocalDate dataAniversario;

    private String cpf;

    private String cnpj;

    @NotBlank
    @Size(min = 2, max = 20)
    private String identificacao;
    
    @NotBlank
    @Size(min = 2, max = 20)
    private String senha;

}
