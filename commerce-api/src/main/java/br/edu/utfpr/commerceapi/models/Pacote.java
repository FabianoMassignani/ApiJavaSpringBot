package br.edu.utfpr.commerceapi.models;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.C;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tb_pacote")
public class Pacote extends BaseEntity {

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pacote_id")
    // @JsonIgnore

    @Column(name = "passeios")
    private List<Passeio> passeios = new ArrayList<>();

}
