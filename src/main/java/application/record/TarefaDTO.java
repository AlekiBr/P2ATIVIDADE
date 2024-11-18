package application.record;

import java.time.LocalDateTime;
import java.util.Set;

import application.model.Colaborador;
import application.model.Tarefa;

public record TarefaDTO(
    long id,
    String titulo,
    String descricao,
    LocalDateTime dataCriacao,
    LocalDateTime dataInicio,
    LocalDateTime dataConclusao,
    Set<Colaborador> colaboradores
) {
    public TarefaDTO(Tarefa tarefa) {
        this(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getDataCriacao(),
            tarefa.getDataInicio(),
            tarefa.getDataConclusao(),
            tarefa.getColaboradores()
        );
    }
}