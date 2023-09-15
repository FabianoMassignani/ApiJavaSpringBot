package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends BaseEntity {

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
}
