
package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspSituacoes;
import caspvale.caspsuporte.atendimento.domain.repository.SituacoesRepository;
import caspvale.caspsuporte.domain.exception.EntidadeNaoEncontradaException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hilton
 */
@Service
public class SituacoesService {
    @Autowired
    private SituacoesRepository repository;

    public CaspSituacoes buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Situação não localizada!")
        );        
    }
    
     public List<CaspSituacoes> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoSituacao"));
    }
}
