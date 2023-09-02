package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping("")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable UUID id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if (pagamento.isPresent()) {
            return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Pagamento savedPagamento = pagamentoRepository.save(pagamento);

        return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable UUID id, @RequestBody Pagamento updatedPagamento) {
        Optional<Pagamento> existingPagamento = pagamentoRepository.findById(id);

        if (existingPagamento.isPresent()) {
            Pagamento pagamento = existingPagamento.get();
            // Atualize os campos do pagamento conforme necessário
            // pagamento.setCampo(updatedPagamento.getCampo());
            pagamentoRepository.save(pagamento);
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable UUID id) {
        Optional<Pagamento> existingPagamento = pagamentoRepository.findById(id);
        
        if (existingPagamento.isPresent()) {
            pagamentoRepository.delete(existingPagamento.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
