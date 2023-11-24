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

import br.edu.utfpr.commerceapi.dto.PacoteDTO;
import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.repositories.PacoteRepository;

@RestController
@RequestMapping("/pacote")
public class PacoteController {

    @Autowired
    PacoteRepository pacoteRepository;

    // Obter todos os pacotes do banco
    @GetMapping(value = { "", "/" })
    public List<Pacote> getAll() {
        return pacoteRepository.findAll();
    }

    // Obter todas as reservas de forma paginada
    @GetMapping("/pages")
    public ResponseEntity<Page<Pacote>> getAllPage(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(pacoteRepository.findAll(pageable));
    }

    // Obter um pacote por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Pacote> pacoteOpt = pacoteRepository
                .findById(UUID.fromString(id));

        return pacoteOpt.isPresent()
                ? ResponseEntity.ok(pacoteOpt.get())
                : ResponseEntity.notFound().build();
    }

    // Inserir 1 pacote
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody PacoteDTO pacoteDTO) {
        var pac = new Pacote(); // pacote que será persistido no DB
        BeanUtils.copyProperties(pacoteDTO, pac);

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pacoteRepository.save(pac));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao criar pacote");
        }
    }

    // Atualizar 1 pacote por ID
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable String id, @RequestBody PacoteDTO pacoteDTO) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        // Buscando o pacote no banco de dados
        var pacote = pacoteRepository.findById(uuid);

        // Verifica se ele existe
        if (pacote.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        var pacoteToUpdate = pacote.get();
        BeanUtils.copyProperties(pacoteDTO, pacoteToUpdate);
        pacoteToUpdate.setAtualizado_em(LocalDateTime.now());

        try {
            return ResponseEntity
                    .ok()
                    .body(pacoteRepository.save(pacoteToUpdate));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao atualizar o passeio.");
        }
    }

    // Deletar 1 pacote por ID
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

        // Buscando o passeio no banco de dados
        var pacote = pacoteRepository.findById(uuid);

        // Verifica se ele existe
        if (pacote.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        try {
            pacoteRepository.delete(pacote.get());

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