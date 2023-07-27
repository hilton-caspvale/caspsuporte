/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */

package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.TiposEntidadesRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
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
public class TiposEntidadesService {

    @Autowired
    private TiposEntidadesRepository repository;

    public CaspTiposEntidades buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Tipo de entidade não localizado!")
        );        
    }

    @Transactional
    public CaspTiposEntidades novo(CaspTiposEntidades input) {
        return repository.save(input);
    }

    @Transactional
    public CaspTiposEntidades editar(CaspTiposEntidades input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspTiposEntidades> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "tipoDescricao"));
    }

}
