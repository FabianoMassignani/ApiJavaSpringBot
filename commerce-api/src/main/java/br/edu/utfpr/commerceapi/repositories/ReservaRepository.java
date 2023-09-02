package br.edu.utfpr.commerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.commerceapi.models.Reserva;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    // Métodos personalizados de consulta podem ser adicionados aqui, se necessário.
}