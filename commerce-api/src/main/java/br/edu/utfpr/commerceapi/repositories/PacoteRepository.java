package br.edu.utfpr.commerceapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.commerceapi.models.Pacote;

public interface PacoteRepository extends JpaRepository <Pacote, UUID> {
    
}
