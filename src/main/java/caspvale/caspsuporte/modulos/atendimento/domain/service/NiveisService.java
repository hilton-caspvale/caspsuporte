/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */

package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspNiveis;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.NiveisRepository;
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
public class NiveisService {

    @Autowired
    private NiveisRepository repository;

    public CaspNiveis buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Nivel não localizado!")
        );        
    }

    @Transactional
    public CaspNiveis novo(CaspNiveis input) {
        return repository.save(input);
    }

    @Transactional
    public CaspNiveis editar(CaspNiveis input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspNiveis> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoNivel"));
    }

}
