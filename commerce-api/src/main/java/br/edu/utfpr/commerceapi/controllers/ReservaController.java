package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.ReservaDTO;
import br.edu.utfpr.commerceapi.models.Reserva;
import br.edu.utfpr.commerceapi.repositories.ReservaRepository;
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
@RequestMapping("/reserva")
public class ReservaController {

  @Autowired
  private ReservaRepository reservaRepository;

  @GetMapping("")
  public ResponseEntity<List<Reserva>> getAllReservas() {
    List<Reserva> reservas = reservaRepository.findAll();

    return new ResponseEntity<>(reservas, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Reserva> getReservaById(@PathVariable String id) {
    Optional<Reserva> reserva = reservaRepository.findById(UUID.fromString(id));

    if (reserva.isPresent()) {
      return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("")
  public ResponseEntity<Reserva> createReserva(
    @RequestBody ReservaDTO reservaDTO
  ) {
    try {
      var reserva = new Reserva();
      BeanUtils.copyProperties(reservaDTO, reserva);

      reserva.setCreatedAt(LocalDateTime.now());
      reserva.setUpdatedAt(LocalDateTime.now());

      Reserva savedReserva = reservaRepository.save(reserva);

      return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reserva> updateReserva(
    @PathVariable UUID id,
    @RequestBody Reserva updatedReserva
  ) {
    Optional<Reserva> existingReserva = reservaRepository.findById(id);

    if (existingReserva.isPresent()) {
      Reserva reserva = existingReserva.get();
      // Atualize os campos da reserva conforme necessário
      // reserva.setCampo(updatedReserva.getCampo());
      reservaRepository.save(reserva);
      return new ResponseEntity<>(reserva, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReserva(@PathVariable UUID id) {
    Optional<Reserva> existingReserva = reservaRepository.findById(id);

    if (existingReserva.isPresent()) {
      reservaRepository.delete(existingReserva.get());
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
