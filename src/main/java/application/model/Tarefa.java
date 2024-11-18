package application.model;

import java.util.Set;

import application.record.TarefaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;



@Entity
@Table(name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(nullable = false)
    public String titulo;

    @Column(nullable = false)
    public String descricao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = true)
    private LocalDateTime dataInicio;

    @Column(nullable = true)
    private LocalDateTime dataConclusao;


    @ManyToMany
    @JoinTable(
        name = "colaborador_tarefa", 
        joinColumns = @JoinColumn(name = "tarefa_id"), 
        inverseJoinColumns = @JoinColumn(name = "colaborador_id") 
    )
    private Set<Colaborador> colaboradores; 


    public Tarefa(TarefaDTO tarefa) {
        this.id = tarefa.id();
        this.titulo = tarefa.titulo();
        this.descricao = tarefa.descricao();
        this.dataCriacao = tarefa.dataCriacao();
        this.dataInicio = tarefa.dataInicio();
        this.dataConclusao = tarefa.dataConclusao();
    }
}