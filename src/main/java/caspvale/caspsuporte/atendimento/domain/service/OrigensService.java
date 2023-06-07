package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspOrigens;
import caspvale.caspsuporte.atendimento.domain.repository.OrigensRepository;
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
public class OrigensService {

    @Autowired
    private OrigensRepository repository;

    public CaspOrigens buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Orgigem não localizada!")
        );        
    }

    @Transactional
    public CaspOrigens novo(CaspOrigens input) {
        return repository.save(input);
    }

    @Transactional
    public CaspOrigens editar(CaspOrigens input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspOrigens> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoOrigem"));
    }

}
