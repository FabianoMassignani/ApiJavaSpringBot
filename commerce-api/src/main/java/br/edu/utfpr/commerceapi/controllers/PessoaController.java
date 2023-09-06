package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.dto.PessoaDTO;
import br.edu.utfpr.commerceapi.models.Pessoa;
import br.edu.utfpr.commerceapi.repositories.PessoaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
        try {
            List<PessoaDTO> pessoas = pessoaRepository.findAll();

            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable UUID id) {
        try {
            Optional<PessoaDTO> pessoa = pessoaRepository.findById(id);

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
    public ResponseEntity<PessoaDTO> createPessoa(@RequestBody PessoaDTO pessoa) {
        try {
            pessoa.setNascimento(LocalDateTime.now());
            PessoaDTO savedPessoa = pessoaRepository.save(pessoa);

            return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable UUID id, @RequestBody PessoaDTO updatedPessoa) {

        try {
            Optional<PessoaDTO> existingPessoa = pessoaRepository.findById(id);

            if (existingPessoa.isPresent()) {
                PessoaDTO pessoa = existingPessoa.get();

                pessoa.setNome(updatedPessoa.getNome());
                pessoa.setEmail(updatedPessoa.getEmail());
                
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
            Optional<PessoaDTO> pessoa = pessoaRepository.findById(id);

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
