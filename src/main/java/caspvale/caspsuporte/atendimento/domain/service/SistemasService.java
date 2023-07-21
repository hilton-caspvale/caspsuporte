/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */

package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspProblemas;
import caspvale.caspsuporte.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.repository.SistemasRepository;
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
public class SistemasService {

    @Autowired
    private SistemasRepository repository;

    public CaspSistemas buscarOuFalhar(Integer id){        
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException("Sistemas não localizado!")
        );        
    }

    @Transactional
    public CaspSistemas novo(CaspSistemas input) {
        return repository.save(input);
    }

    @Transactional
    public CaspSistemas editar(CaspSistemas input, CaspSistemas sistemaAtual) {
        input.setCaspChamadosList(sistemaAtual.getCaspChamadosList());
        input.setCaspUsuariosConfigList(sistemaAtual.getCaspUsuariosConfigList());
        input.setCaspUsuariosList(sistemaAtual.getCaspUsuariosList());
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        //repository.deleteById(id);
    }

    public List<CaspSistemas> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoSistema"));
    }

    public List<CaspSistemas> sistemasDoUsuario(String situacao, CaspUsuarios usuario) {
        return repository.sistemasDoUsuario(situacao, usuario);
    }
}
