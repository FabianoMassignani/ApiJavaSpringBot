package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.models.Pessoa;
import br.edu.utfpr.commerceapi.repositories.PessoaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;

  // Pegar com paginacao

  @GetMapping({ "", "/" })
  public ResponseEntity<List<Pessoa>> getAllPessoas() {
    try {
      List<Pessoa> pessoas = pessoaRepository.findAll();

      return new ResponseEntity<>(pessoas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getPessoaById(@PathVariable UUID id) {
    try {
      Optional<Pessoa> pessoa = pessoaRepository.findById(id);

      if (pessoa.isPresent()) {
        return ResponseEntity.ok().body(pessoa.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createPessoa(@RequestBody Pessoa pessoa) {
    try {
      

      pessoa.setCreatedAt(LocalDateTime.now());
      pessoa.setUpdatedAt(LocalDateTime.now());

      Pessoa savedPessoa = pessoaRepository.save(pessoa);

      return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updatePessoa(
    @PathVariable UUID id,
    @RequestBody Pessoa updatedPessoa
  ) {
    try {
      var existingPessoa = pessoaRepository.findById(id);

      if (existingPessoa.isPresent()) {
        Pessoa pessoa = existingPessoa.get();

        pessoa.setNome(updatedPessoa.getNome());
        pessoa.setEmail(updatedPessoa.getEmail());
        pessoa.setNascimento(updatedPessoa.getNascimento());
        pessoa.setCpf(updatedPessoa.getCpf());
        pessoa.setCnpj(updatedPessoa.getCnpj());
        pessoa.setPassword(updatedPessoa.getPassword());
        pessoa.setTelefone(updatedPessoa.getTelefone());
        pessoa.setIsPessoaFisica(updatedPessoa.getIsPessoaFisica());
        pessoa.setUpdatedAt(LocalDateTime.now());


        pessoaRepository.save(pessoa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletePessoa(@PathVariable UUID id) {
    try {
      Optional<Pessoa> pessoa = pessoaRepository.findById(id);

      if (pessoa.isPresent()) {
        pessoaRepository.deleteById(id);
        return ResponseEntity.notFound().build();
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
