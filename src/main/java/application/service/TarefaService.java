package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.model.Tarefa;
import application.record.TarefaDTO;
import application.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepo;

    public List<TarefaDTO> getAll() {
        return tarefaRepo.findAll().stream()
                .map(TarefaDTO::new)
                .toList();
    }

    public TarefaDTO insert(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO);
        return new TarefaDTO(tarefaRepo.save(tarefa));
    }

    public TarefaDTO update(long id, TarefaDTO tarefaDTO) {
        Optional<Tarefa> optionalTarefa = tarefaRepo.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setTitulo(tarefaDTO.titulo());
            tarefa.setDescricao(tarefaDTO.descricao());
            tarefa.setDataInicio(tarefaDTO.dataInicio());
            tarefa.setDataConclusao(tarefaDTO.dataConclusao());
            return new TarefaDTO(tarefaRepo.save(tarefa));
        }
        return null; 
    }

    public void delete(long id) {
        tarefaRepo.deleteById(id);
    }

    public Optional<TarefaDTO> getById(long id) {
        return tarefaRepo.findById(id).map(TarefaDTO::new);
    }
}