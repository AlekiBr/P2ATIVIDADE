package application.controller;

import application.record.TarefaDTO;
import application.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> getAllTarefas() {
        List<TarefaDTO> tarefas = tarefaService.getAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> getTarefaById(@PathVariable long id) {
        Optional<TarefaDTO> tarefa = tarefaService.getById(id);
        return tarefa.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> createTarefa(@RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO createdTarefa = tarefaService.insert(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> updateTarefa(@PathVariable long id, @RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO updatedTarefa = tarefaService.update(id, tarefaDTO);
        return (updatedTarefa != null)
                ? ResponseEntity.ok(updatedTarefa)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}