package br.edu.utfpr.commerceapi.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.commerceapi.models.Passeio;
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

public class PacoteDTO {
    
    @NotBlank
    private double preco;

    @NotBlank
    private List<Passeio> passeios = new ArrayList<>();
}
