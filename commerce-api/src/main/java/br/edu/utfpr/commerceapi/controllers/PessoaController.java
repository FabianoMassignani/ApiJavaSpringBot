package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.PessoaDTO;
import br.edu.utfpr.commerceapi.models.Pessoa;
import br.edu.utfpr.commerceapi.repositories.PessoaRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;

  // Pegar com paginacao

  @GetMapping("/pages")
  public ResponseEntity<Page<Pessoa>> getAllPage(
    @PageableDefault(
      page = 0,
      size = 10,
      sort = "nome",
      direction = Sort.Direction.ASC
    ) Pageable pageable
  ) {
    return ResponseEntity.ok().body(pessoaRepository.findAll(pageable));
  }

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
  public ResponseEntity<Object> getPessoaById(@PathVariable String id) {
    try {
      Optional<Pessoa> pessoa = pessoaRepository.findById(UUID.fromString(id));

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
  public ResponseEntity<Object> createPessoa(
    @Valid @RequestBody PessoaDTO pessoaDTO
  ) {
    try {
      var pessoa = new Pessoa();
      BeanUtils.copyProperties(pessoaDTO, pessoa);

      Pessoa savedPessoa = pessoaRepository.save(pessoa);

      return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(
    @PathVariable String id,
    @RequestBody PessoaDTO personDTO
  ) {
    try {
      Optional<Pessoa> existingPessoa = pessoaRepository.findById(
        UUID.fromString(id)
      );

      if (existingPessoa.isPresent()) {
        Pessoa pessoa = existingPessoa.get();

        BeanUtils.copyProperties(personDTO, pessoa);

        pessoa.setUpdatedAt(LocalDateTime.now());

        pessoaRepository.save(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex
  ) {
    Map<String, String> errors = new HashMap<>();

    ex
      .getBindingResult()
      .getAllErrors()
      .forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
      });

    return errors;
  }
}
