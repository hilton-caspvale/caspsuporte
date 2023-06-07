package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.domain.exception.AreaNaoEncontradaException;
import caspvale.caspsuporte.atendimento.domain.repository.AreasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class AreasService {

    @Autowired
    private AreasRepository repository;

    public CaspAreas buscarOuFalhar(Integer id){     
        return repository.findById(id).orElseThrow(() ->
                new AreaNaoEncontradaException("Área não localizada!")
        );        
    }

    @Transactional
    public CaspAreas novo(CaspAreas input) {
        return repository.save(input);
    }

    @Transactional
    public CaspAreas editar(CaspAreas input, CaspAreas areaAtual) {
        input.setCaspChamadosList(areaAtual.getCaspChamadosList());
        input.setCaspUsuariosConfigList(areaAtual.getCaspUsuariosConfigList());
        input.setCaspUsuariosList(areaAtual.getCaspUsuariosList());
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspAreas> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoArea"));
    }
    
   public List<CaspAreas> areasDoUsuario(String situacao, CaspUsuarios usuario) {
        return repository.areasDoUsuario(situacao, usuario);
    } 

}
