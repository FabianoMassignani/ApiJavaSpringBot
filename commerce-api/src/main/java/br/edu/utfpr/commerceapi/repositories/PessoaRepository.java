package br.edu.utfpr.commerceapi.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.commerceapi.dto.PessoaDTO;

public interface PessoaRepository extends JpaRepository<PessoaDTO, UUID> {

}
