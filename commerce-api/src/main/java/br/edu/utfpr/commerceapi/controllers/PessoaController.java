package br.edu.utfpr.commerceapi.controllers;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.dto.PessoaDTO;
import br.edu.utfpr.commerceapi.models.Pessoa;
import br.edu.utfpr.commerceapi.repositories.PessoaRepository;
import jakarta.validation.Valid;
import br.edu.utfpr.commerceapi.security.JwtUtil;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Obter todas as pessoas do banco
    @GetMapping(value = { "", "/" })
    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    // Obter todas as pessoas paginadas
    @GetMapping("/pages")
    public ResponseEntity<Page<Pessoa>> getAllPage(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok().body(pessoaRepository.findAll(pageable));
    }

    // Obter 1 pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository
                .findById(UUID.fromString(id));

        return pessoaOpt.isPresent()
                ? ResponseEntity.ok(pessoaOpt.get())
                : ResponseEntity.notFound().build();
    }

    // Obter 1 pessoa por ID
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getByEmail(@PathVariable String email) {
        Optional<Pessoa> pessoaOpt = pessoaRepository
                .findByEmail(email);

        return pessoaOpt.isPresent()
                ? ResponseEntity.ok(pessoaOpt.get())
                : ResponseEntity.notFound().build();
    }

    // Inserir 1 pessoa
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody PessoaDTO pessoaDTO) {
        var pes = new Pessoa(); // pessoa para persistir no DB
        BeanUtils.copyProperties(pessoaDTO, pes);
        pes.setSenha(passwordEncoder.encode(pessoaDTO.getSenha()));

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pessoaRepository.save(pes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao criar pessoa.");
        }
    }

    // Atualizar 1 pessoa por ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody PessoaDTO pessoaDTO) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        // Buscando a pessoa no banco de dados
        var pessoa = pessoaRepository.findById(uuid);

        // Verifica se ela existe
        if (pessoa.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        var pessoaToUpdate = pessoa.get();
        BeanUtils.copyProperties(pessoaDTO, pessoaToUpdate);
        pessoaToUpdate.setAtualizado_em(LocalDateTime.now());

        try {
            return ResponseEntity
                    .ok()
                    .body(pessoaRepository.save(pessoaToUpdate));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao atualizar pessoa.");
        }
    }

    // Deletar 1 pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        var pessoa = pessoaRepository.findById(uuid);

        if (pessoa.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        try {
            pessoaRepository.delete(pessoa.get());

            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
