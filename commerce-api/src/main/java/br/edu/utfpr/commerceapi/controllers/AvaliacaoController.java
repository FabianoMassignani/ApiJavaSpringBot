package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.AvaliacaoDTO;
import br.edu.utfpr.commerceapi.models.Avaliacao;
import br.edu.utfpr.commerceapi.repositories.AvaliacaoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

  @Autowired
  private AvaliacaoRepository avaliacaoRepository;

  @GetMapping("")
  public ResponseEntity<List<Avaliacao>> getAllAvaliacoes() {
    try {
      List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

      return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping({ "/id" })
  public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable String id) {
    try {
      Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(
        UUID.fromString(id)
      );

      if (avaliacao.isPresent()) {
        return new ResponseEntity<>(avaliacao.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<Avaliacao> createAvaliacao(
    @RequestBody AvaliacaoDTO avaliacaoDTO
  ) {
    try {
      var avaliacao = new Avaliacao();
      BeanUtils.copyProperties(avaliacaoDTO, avaliacao);

      Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

      return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Avaliacao> updateAvaliacao(
    @PathVariable UUID id,
    @RequestBody Avaliacao updatedAvaliacao
  ) {
    try {
      Optional<Avaliacao> existingAvaliacao = avaliacaoRepository.findById(id);

      if (existingAvaliacao.isPresent()) {
        Avaliacao avaliacao = existingAvaliacao.get();
        // Atualize os campos da avaliação conforme necessário
        // avaliacao.setCampo(updatedAvaliacao.getCampo());
        avaliacaoRepository.save(avaliacao);
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAvaliacao(@PathVariable UUID id) {
    try {
      Optional<Avaliacao> existingAvaliacao = avaliacaoRepository.findById(id);

      if (existingAvaliacao.isPresent()) {
        avaliacaoRepository.delete(existingAvaliacao.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
