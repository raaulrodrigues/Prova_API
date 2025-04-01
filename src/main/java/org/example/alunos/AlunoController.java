package org.example.alunos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alunos")
@Tag(name = "Alunos", description = "Operações CRUD para alunos")
public class AlunoController {

    private final AlunoRepository repository;

    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Operation(summary = "Cria um novo aluno")
    public Aluno criar(@Valid @RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    @GetMapping
    @Operation(summary = "Lista todos os alunos")
    public List<Aluno> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um aluno pelo ID")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable String id) {
        Optional<Aluno> aluno = repository.findById(id);
        return aluno.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um aluno existente")
    public ResponseEntity<Aluno> atualizar(@PathVariable String id, @Valid @RequestBody Aluno alunoAtualizado) {
        return repository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setTelefone(alunoAtualizado.getTelefone());
                    aluno.setEmail(alunoAtualizado.getEmail());
                    aluno.setEndereco(alunoAtualizado.getEndereco());
                    Aluno atualizado = repository.save(aluno);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um aluno")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}