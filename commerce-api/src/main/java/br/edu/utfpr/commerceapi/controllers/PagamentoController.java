package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;
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
@RequestMapping("/pagamento")
public class PagamentoController {

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @GetMapping("")
  public ResponseEntity<List<Pagamento>> getAllPagamentos() {
    try {
      List<Pagamento> pagamentos = pagamentoRepository.findAll();

      return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pagamento> getPagamentoById(@PathVariable String id) {
    try {
      Optional<Pagamento> pagamento = pagamentoRepository.findById(
        UUID.fromString(id)
      );

      if (pagamento.isPresent()) {
        return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<Pagamento> createPagamento(
    @RequestBody PagamentoDTO pagamentoDTO
  ) {
    try {
      var pagamento = new Pagamento();
      BeanUtils.copyProperties(pagamentoDTO, pagamento);

      Pagamento savedPagamento = pagamentoRepository.save(pagamento);

      return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pagamento> updatePagamento(
    @PathVariable UUID id,
    @RequestBody Pagamento updatedPagamento
  ) {
    try {
      Optional<Pagamento> existingPagamento = pagamentoRepository.findById(id);

      if (existingPagamento.isPresent()) {
        Pagamento pagamento = existingPagamento.get();

        pagamentoRepository.save(pagamento);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePagamento(@PathVariable UUID id) {
    try {
      Optional<Pagamento> existingPagamento = pagamentoRepository.findById(id);

      if (existingPagamento.isPresent()) {
        pagamentoRepository.delete(existingPagamento.get());
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
