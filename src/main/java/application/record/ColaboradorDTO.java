package application.record;

import application.model.Colaborador;
import java.util.Set;
import java.util.stream.Collectors;

public record ColaboradorDTO(long id, String nome, Set<TarefaDTO> tarefas) {
    public ColaboradorDTO(Colaborador colaborador) {
        this(colaborador.getId(), colaborador.getNome(), 
             colaborador.getTarefas().stream()
                         .map(TarefaDTO::new) // 
                         .collect(Collectors.toSet()));
    }
}