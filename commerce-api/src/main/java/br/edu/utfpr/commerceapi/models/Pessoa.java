package br.edu.utfpr.commerceapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends BaseEntity implements UserDetails {

  // @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "nome", length = 150, nullable = false)
  private String nome;

  @Column(name = "email", length = 150, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 150, nullable = false)
  private String password;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "dataNascimento")
  private LocalDateTime dataNascimento;

  @Column(name = "telefone")
  private String telefone;

  @Column(name = "isPessoaFisica", columnDefinition = "boolean default false")
  private Boolean isPessoaFisica;

  @ManyToMany(mappedBy = "pessoas")
  private List<Reserva> reservas;

  @OneToMany(mappedBy = "pessoa")
  private List<Avaliacao> avaliacoes;

  @Override
  @JsonIgnore
  public String getUsername() {
    return this.email;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }

  @Override
  @JsonIgnore
  public List<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    return authorities;
  }
}
