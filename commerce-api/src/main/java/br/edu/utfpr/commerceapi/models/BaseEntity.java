package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    private UUID id;

    @Column(name = "criado_em")
    private LocalDateTime criado_em;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizado_em;

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.atualizado_em = criado_em = LocalDateTime.now();
    }
}
