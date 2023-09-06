package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "nascimento")
    private LocalDateTime nascimento;

    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @Column(name = "telefone", length = 150, nullable = false)
    private String telefone;

    @Column(name = "isPessoaFisica")
    private Boolean isPessoaFisica;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;
}
