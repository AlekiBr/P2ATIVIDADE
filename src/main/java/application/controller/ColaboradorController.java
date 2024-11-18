package application.controller;

import application.record.ColaboradorDTO;
import application.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> getAllColaboradores() {
        List<ColaboradorDTO> colaboradores = colaboradorService.getAll();
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> getColaboradorById(@PathVariable long id) {
        Optional<ColaboradorDTO> colaborador = colaboradorService.getById(id);
        return colaborador.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColaboradorDTO> createColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
        ColaboradorDTO createdColaborador = colaboradorService.insert(colaboradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdColaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> updateColaborador(@PathVariable long id, @RequestBody ColaboradorDTO colaboradorDTO) {
        ColaboradorDTO updatedColaborador = colaboradorService.update(id, colaboradorDTO);
        return (updatedColaborador != null)
                ? ResponseEntity.ok(updatedColaborador)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColaborador(@PathVariable long id) {
        colaboradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}