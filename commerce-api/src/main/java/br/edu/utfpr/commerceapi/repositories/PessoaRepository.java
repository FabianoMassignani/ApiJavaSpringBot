package br.edu.utfpr.commerceapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.commerceapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    public boolean existsByIdAndEmail(UUID id, String email);

    public boolean existsByEmail(String email);

    public boolean existsByEmailAndPassword(String email, String password);

    public Optional<Pessoa> findByEmail(String email);

    public Optional<Pessoa> findByEmailAndPassword(String email, String password);

    public List<Pessoa> findByNome(String nome);
}
