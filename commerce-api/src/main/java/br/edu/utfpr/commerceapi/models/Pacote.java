package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "tb_pacote")
public class Pacote extends BaseEntity {

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "pacote", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passeio> passeios = new ArrayList<>();
}
