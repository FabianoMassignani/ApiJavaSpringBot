package br.edu.utfpr.commerceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {
    
    @NotBlank
    @Size(min = 3)
    public String username;

    @NotBlank
    @Size(min = 3)
    public String password;

}
