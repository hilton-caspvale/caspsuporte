/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposArquivos;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.TiposArquivosRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
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

    public CaspTiposArquivos buscarOuFalhar(Integer id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Tipo de arquivo não localizado!")
        );
    }

    @Transactional
    public CaspTiposArquivos novo(CaspTiposArquivos input) {
        if (input.getDescricaoTipoArquivo().equalsIgnoreCase("IMAGENS")) {
            throw new NegocioException("Descrição [IMAGENS] em uso!");
        }
        return repository.save(input);
    }

    @Transactional
    public CaspTiposArquivos editar(CaspTiposArquivos input) {
        if (input.getITipoArquivo() == 3) {
            throw new NegocioException("Esse cadastro não pode ser alterado!");
        }
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        if (id == 3) {
            throw new NegocioException("Esse cadastro não pode ser excluído!");
        }
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspTiposArquivos> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoTipoArquivo"));
    }

}
