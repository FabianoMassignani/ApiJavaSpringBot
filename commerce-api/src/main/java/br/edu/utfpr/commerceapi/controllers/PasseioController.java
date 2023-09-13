package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.PasseioDTO;
import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.repositories.PasseioRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
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
@RequestMapping("/passeio")
public class PasseioController {

  @Autowired
  private PasseioRepository passeioRepository;

  @GetMapping("")
  public ResponseEntity<List<Passeio>> getAllPasseios() {
    try {
      List<Passeio> passeios = passeioRepository.findAll();

      return new ResponseEntity<>(passeios, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Passeio> getPasseioById(@PathVariable String id) {
    try {
      Optional<Passeio> passeio = passeioRepository.findById(
        UUID.fromString(id)
      );

      if (passeio.isPresent()) {
        return new ResponseEntity<>(passeio.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createPessoa(
    @Valid @RequestBody PasseioDTO PasseioDTO
  ) {
    try {
      var passeio = new Passeio();
      BeanUtils.copyProperties(PasseioDTO, passeio);

      passeio.setCreatedAt(LocalDateTime.now());
      passeio.setUpdatedAt(LocalDateTime.now());

      Passeio savedPasseio = passeioRepository.save(passeio);

      return new ResponseEntity<>(savedPasseio, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Passeio> updatePasseio(
    @PathVariable UUID id,
    @RequestBody Passeio updatedPasseio
  ) {
    try {
      Optional<Passeio> existingPasseio = passeioRepository.findById(id);

      if (existingPasseio.isPresent()) {
        Passeio passeio = existingPasseio.get();
        // Atualize os campos do passeio conforme necessário
        // passeio.setCampo(updatedPasseio.getCampo());
        passeioRepository.save(passeio);
        return new ResponseEntity<>(passeio, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePasseio(@PathVariable UUID id) {
    try {
      Optional<Passeio> existingPasseio = passeioRepository.findById(id);

      if (existingPasseio.isPresent()) {
        passeioRepository.delete(existingPasseio.get());
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
