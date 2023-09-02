package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Avaliacao;
import br.edu.utfpr.commerceapi.repositories.AvaliacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping("")
    public ResponseEntity<List<Avaliacao>> getAllAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping({"/id"})
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable UUID id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (avaliacao.isPresent()) {
            return new ResponseEntity<>(avaliacao.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);

        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable UUID id, @RequestBody Avaliacao updatedAvaliacao) {
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable UUID id) {
        Optional<Avaliacao> existingAvaliacao = avaliacaoRepository.findById(id);
        
        if (existingAvaliacao.isPresent()) {
            avaliacaoRepository.delete(existingAvaliacao.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
