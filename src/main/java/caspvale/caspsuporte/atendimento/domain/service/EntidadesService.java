package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.repository.EntidadesRepository;
import caspvale.caspsuporte.domain.exception.EntidadeNaoEncontradaException;
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
public class EntidadesService {

    @Autowired
    private EntidadesRepository entidadesRepoitory;

    public CaspEntidades buscarOuFalhar(Integer id){        
        return entidadesRepoitory.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Entidade não localizada!")
        );        
    }

    @Transactional
    public CaspEntidades novo(CaspEntidades input) {
        return entidadesRepoitory.save(input);
    }

    @Transactional
    public CaspEntidades editar(CaspEntidades input, CaspEntidades entidadeAnterior) {
        input.setCaspChamadosList(entidadeAnterior.getCaspChamadosList());
        input.setCaspUsuariosConfigList(entidadeAnterior.getCaspUsuariosConfigList());
        input.setCaspUsuariosList(entidadeAnterior.getCaspUsuariosList());
        return entidadesRepoitory.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        entidadesRepoitory.deleteById(id);
    }

    public List<CaspEntidades> listar() {
        return entidadesRepoitory.findAll(Sort.by(Sort.Direction.ASC, "nomeEntidade"));
        //return entidadesRepoitory.findAll();
    }
    
    public List<CaspEntidades> entidadesDoUsuario(String situacao, CaspUsuarios usuario){
            return entidadesRepoitory.entidadesDoUsuario(situacao, usuario);
    }

}
