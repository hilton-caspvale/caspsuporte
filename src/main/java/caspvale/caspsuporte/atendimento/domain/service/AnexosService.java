/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.common.Permissoes;
import caspvale.caspsuporte.atendimento.domain.model.CaspAnexos;
import caspvale.caspsuporte.atendimento.domain.repository.AnexosRepository;
import caspvale.caspsuporte.domain.exception.AnexoNaoEncontradoException;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class AnexosService {

    private final AnexosRepository anexosRepository;
    private final Permissoes permissoes;

    public AnexosService(AnexosRepository anexosRepository, Permissoes permissoes) {
        this.anexosRepository = anexosRepository;
        this.permissoes = permissoes;
    }

    public CaspAnexos buscarOuFalhar(Integer id) {
        CaspAnexos caspAnexo = anexosRepository.findById(id).orElseThrow(()
                -> new AnexoNaoEncontradoException("Comentário ou arquivo não localizado!")
        );
        return caspAnexo;
    }

    public boolean permiteExcluirAnexo(CaspAnexos caspAnexo, Integer iUsuarioLogado) {
        return Objects.equals(caspAnexo.getIUsuario().getIUsuario(), iUsuarioLogado);
    }

    @Transactional
    public boolean deletarAnexo(Integer iAnexo) {
        try {
            anexosRepository.deleteById(iAnexo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public CaspAnexos gravar(CaspAnexos caspAnexo) {
        return anexosRepository.saveAndFlush(caspAnexo);
    }

}
