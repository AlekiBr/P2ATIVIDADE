package application.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import application.record.ColaboradorDTO;

@Entity
@Table(name = "colaborador")
@Getter
@Setter
@NoArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "colaboradores") // Relacionamento com Tarefa
    private Set<Tarefa> tarefas;

    public Colaborador(ColaboradorDTO colaborador) {
        this.id = colaborador.id();
        this.nome = colaborador.nome();
    }
}