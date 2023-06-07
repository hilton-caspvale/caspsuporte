/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.domain.model.CaspTiposUsuarios;
import caspvale.caspsuporte.atendimento.domain.repository.TiposUsuariosRepository;
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
public class TiposUsuariosService {

    @Autowired
    private TiposUsuariosRepository repository;

    public CaspTiposUsuarios buscarOuFalhar(Integer id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Tipo de usuário não localizado!")
        );
    }

    @Transactional
    public CaspTiposUsuarios novo(CaspTiposUsuarios input) {
        return repository.save(input);
    }

    @Transactional
    public CaspTiposUsuarios editar(CaspTiposUsuarios input) {
        return repository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        buscarOuFalhar(id);
        repository.deleteById(id);
    }

    public List<CaspTiposUsuarios> listar() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "descricaoTipoUsuario"));
    }

    public CaspTiposUsuarios buscarPorDescricao(String descricaoTipo) {
        return repository.findByDescricaoTipoUsuario(descricaoTipo);
    }

    public CaspTiposUsuarios buscarTiposClientes() {
        return repository.buscarTiposClientes();
    }

    public List<CaspTiposUsuarios> buscarTiposNaoClientes() {
        return repository.buscarTiposNaoClientes();
    }

}
