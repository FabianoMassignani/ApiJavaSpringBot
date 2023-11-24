package br.edu.utfpr.commerceapi.models;

import java.util.ArrayList;
import java.util.List;

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
    
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "pacote")
    //private List<Reserva> reservas = new ArrayList<Reserva>();

    //@ManyToOne
    //@JoinColumn(name = "pacote_id")
    //private Passeio passeio;

    @Column(name = "preco")
    private Double preco;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pacote_id")
    //@JsonIgnore
    
    private List<Passeio> passeios = new ArrayList<>();
    
}
