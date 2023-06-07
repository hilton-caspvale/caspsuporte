/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */

package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspTiposArquivos;
import caspvale.caspsuporte.atendimento.domain.repository.TiposArquivosRepository;
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
public class TiposArquivosService {

    @Autowired
    private TiposArquivosRepository repository;

    public CaspTiposArquivos buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Tipo de arquivo não localizado!")
        );        
    }

    @Transactional
    public CaspTiposArquivos novo(CaspTiposArquivos input) {
        return repository.save(input);
    }

    @Transactional
    public CaspTiposArquivos editar(CaspTiposArquivos input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspTiposArquivos> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoTipoArquivo"));
    }

}
