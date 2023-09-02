package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.repositories.PasseioRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/passeio")
public class PasseioController {

    @Autowired
    private PasseioRepository passeioRepository;

    @GetMapping("")
    public ResponseEntity<List<Passeio>> getAllPasseios() {
        List<Passeio> passeios = passeioRepository.findAll();
        return new ResponseEntity<>(passeios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passeio> getPasseioById(@PathVariable UUID id) {
        Optional<Passeio> passeio = passeioRepository.findById(id);
        if (passeio.isPresent()) {
            return new ResponseEntity<>(passeio.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Passeio> createPasseio(@RequestBody Passeio passeio) {
        Passeio savedPasseio = passeioRepository.save(passeio);
        return new ResponseEntity<>(savedPasseio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passeio> updatePasseio(@PathVariable UUID id, @RequestBody Passeio updatedPasseio) {
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasseio(@PathVariable UUID id) {
        Optional<Passeio> existingPasseio = passeioRepository.findById(id);
        if (existingPasseio.isPresent()) {
            passeioRepository.delete(existingPasseio.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
