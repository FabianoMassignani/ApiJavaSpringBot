package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.*;
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
@Table(name = "tb_passeio")
public class Passeio extends BaseEntity {

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "origem", length = 150, nullable = false)
    private String origem;

    @Column(name = "destino", length = 150, nullable = false)
    private String destino;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "valor", nullable = false)
    private double valor;

}
