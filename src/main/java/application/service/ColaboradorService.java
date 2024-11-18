package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.model.Colaborador;
import application.record.ColaboradorDTO;
import application.repository.ColaboradorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    public List<ColaboradorDTO> getAll() {
        return colaboradorRepo.findAll().stream()
                .map(ColaboradorDTO::new)
                .toList();
    }

    public ColaboradorDTO insert(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador(colaboradorDTO);
        return new ColaboradorDTO(colaboradorRepo.save(colaborador));
    }

    public ColaboradorDTO update(long id, ColaboradorDTO colaboradorDTO) {
        Optional<Colaborador> optionalColaborador = colaboradorRepo.findById(id);
        if (optionalColaborador.isPresent()) {
            Colaborador colaborador = optionalColaborador.get();
            colaborador.setNome(colaboradorDTO.nome());
            return new ColaboradorDTO(colaboradorRepo.save(colaborador));
        }
        return null; 
    }

    public void delete(long id) {
        colaboradorRepo.deleteById(id);
    }

    public Optional<ColaboradorDTO> getById(long id) {
        return colaboradorRepo.findById(id).map(ColaboradorDTO::new);
    }
}