/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */

package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspPrioridades;
import caspvale.caspsuporte.atendimento.domain.repository.PrioridadesRepository;
import caspvale.caspsuporte.domain.exception.EntidadeNaoEncontradaException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class PrioridadesService {

    @Autowired
    private PrioridadesRepository repository;

    public CaspPrioridades buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Prioridade não localizada!")
        );        
    }

    @Transactional
    public CaspPrioridades novo(CaspPrioridades input) {
        return repository.save(input);
    }

    @Transactional
    public CaspPrioridades editar(CaspPrioridades input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspPrioridades> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoPrioridade"));
    }

}
