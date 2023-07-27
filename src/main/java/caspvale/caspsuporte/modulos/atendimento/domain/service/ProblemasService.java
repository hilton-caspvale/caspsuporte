package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspProblemas;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.ProblemasRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
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
public class ProblemasService {

    @Autowired
    private ProblemasRepository repository;

    public CaspProblemas buscarOuFalhar(Integer id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Problema não localizado!")
        );
    }

    @Transactional
    public CaspProblemas novo(CaspProblemas input) {
        return repository.save(input);
    }

    @Transactional
    public CaspProblemas editar(CaspProblemas input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspProblemas> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoProblema"));
    }

    public List<CaspProblemas> listarPorSituacao(String situacao) {
        return repository.findAllBySituacaoProblema(situacao, Sort.by(Sort.Direction.ASC, "descricaoProblema"));
    }

}
