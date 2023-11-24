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

import br.edu.utfpr.commerceapi.dto.PasseioDTO;
import br.edu.utfpr.commerceapi.models.Passeio;
import br.edu.utfpr.commerceapi.repositories.PasseioRepository;

@RestController
@RequestMapping("/passeio")
public class PasseioController {

    @Autowired
    PasseioRepository passeioRepository;

    // Obter todos os passeios do banco
    @GetMapping(value = { "", "/" })
    public List<Passeio> getAll() {
        return passeioRepository.findAll();
    }

    // Obter todos os passeios de forma paginada
    @GetMapping("/pages")
    public ResponseEntity<Page<Passeio>> getAllPage(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(passeioRepository.findAll(pageable));
    }

    // Obter um passeio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Passeio> passeioOpt = passeioRepository
                .findById(UUID.fromString(id));

        return passeioOpt.isPresent()
                ? ResponseEntity.ok(passeioOpt.get())
                : ResponseEntity.notFound().build();
    }

    // Inserir 1 passeio
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody PasseioDTO passeioDTO) {
        var pas = new Passeio(); // passeio que será persistido no DB
        BeanUtils.copyProperties(passeioDTO, pas);

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(passeioRepository.save(pas));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao criar passeio");
        }
    }

    // Atualizar 1 passeio por ID
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable String id, @RequestBody PasseioDTO passeioDTO) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        // Buscando o passeio no banco de dados
        var passeio = passeioRepository.findById(uuid);

        // Verifica se ele existe
        if (passeio.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        var passeioToUpdate = passeio.get();
        BeanUtils.copyProperties(passeioDTO, passeioToUpdate);
        passeioToUpdate.setAtualizado_em(LocalDateTime.now());

        try {
            return ResponseEntity
                    .ok()
                    .body(passeioRepository.save(passeioToUpdate));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao atualizar o passeio.");
        }
    }

    // Deletar 1 passeio por ID
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
        var passeio = passeioRepository.findById(uuid);

        // Verifica se ele existe
        if (passeio.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        try {
            passeioRepository.delete(passeio.get());

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
