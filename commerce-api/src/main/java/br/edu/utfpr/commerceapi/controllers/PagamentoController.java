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

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    // Obter todos os pagamentos do banco
    @GetMapping(value = { "", "/" })
    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    // Obter todos pagamentos de forma paginada
    @GetMapping("/pages")
    public ResponseEntity<Page<Pagamento>> getAllPage(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(pagamentoRepository.findAll(pageable));
    }

    // Obter um pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Pagamento> pagamentoOpt = pagamentoRepository
                .findById(UUID.fromString(id));

        return pagamentoOpt.isPresent()
                ? ResponseEntity.ok(pagamentoOpt.get())
                : ResponseEntity.notFound().build();
    }

    // Inserir 1 pagamento
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody PagamentoDTO pagamentoDTO) {
        var pag = new Pagamento(); // pagamento que será persistido no DB
        BeanUtils.copyProperties(pagamentoDTO, pag);

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pagamentoRepository.save(pag));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao criar pagamento");
        }
    }

    // Atualizar 1 pagamento por ID
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable String id, @RequestBody PagamentoDTO pagamentoDTO) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        // Buscando o pagamento no banco de dados
        var pagamento = pagamentoRepository.findById(uuid);

        // Verifica se ele existe
        if (pagamento.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        var pagamentoToUpdate = pagamento.get();
        BeanUtils.copyProperties(pagamentoDTO, pagamentoToUpdate);
        pagamentoToUpdate.setAtualizado_em(LocalDateTime.now());

        try {
            return ResponseEntity
                    .ok()
                    .body(pagamentoRepository.save(pagamentoToUpdate));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao atualizar o pagamento.");
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
        var pagamento = pagamentoRepository.findById(uuid);

        // Verifica se ele existe
        if (pagamento.isEmpty())
            return ResponseEntity
                    .notFound()
                    .build();

        try {
            pagamentoRepository.delete(pagamento.get());

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