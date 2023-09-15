package br.edu.utfpr.commerceapi.dto;

 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthDTO {

  @NotNull
  @Size(min = 6)
  public String username;

  @NotNull
  @Size(min = 6)
  public String password;
}
