/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspArquivos;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.ArquivosRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.AnexoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class ArquivosService {

    private final ArquivosRepository arquivosRepository;

    public ArquivosService(ArquivosRepository arquivosRepository) {
        this.arquivosRepository = arquivosRepository;
    }

    public CaspArquivos buscarOuFalhar(Integer id) {
        CaspArquivos caspArquivo = arquivosRepository.findById(id).orElseThrow(()
                -> new AnexoNaoEncontradoException("Comentário ou arquivo não localizado!")
        );
        return caspArquivo;
    }

    @Transactional
    public boolean deletarArquivo(Integer iArquivo) {
        try {
            arquivosRepository.deleteById(iArquivo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public CaspArquivos gravar(CaspArquivos caspArquivo) {
        return arquivosRepository.saveAndFlush(caspArquivo);
    }
    
}
