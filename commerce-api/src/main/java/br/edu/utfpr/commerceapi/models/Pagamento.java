package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_pagamento")
public class Pagamento extends BaseEntity {

    @Column(name = "valor", nullable = false)
    private double valor;
    
    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;
}
