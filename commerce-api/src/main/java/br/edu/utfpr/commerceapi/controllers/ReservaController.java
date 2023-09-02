package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Reserva;
import br.edu.utfpr.commerceapi.repositories.ReservaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Reserva> getReservaById(@PathVariable UUID id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);

        if (reserva.isPresent()) {
            return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva savedReserva = reservaRepository.save(reserva);

        return new ResponseEntity<>(savedReserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable UUID id, @RequestBody Reserva updatedReserva) {
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
}
