/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.UsuariosRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
import caspvale.caspsuporte.modulos.atendimento.exception.UsuarioRestritoException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class UsuariosClientesService {

    private final UsuariosRepository usuariosRepository;
    private final TiposUsuariosService tiposUsuariosService;

    public UsuariosClientesService(UsuariosRepository usuariosRepository, TiposUsuariosService tiposUsuariosService) {
        this.usuariosRepository = usuariosRepository;
        this.tiposUsuariosService = tiposUsuariosService;
    }

    @Transactional
    private CaspUsuarios atribuiTipoCliente(CaspUsuarios usuario) {        
        CaspTiposUsuarios tiposUsuarios = tiposUsuariosService.buscarPorDescricao("CLIENTE");
        usuario.setITipoUsuario(tiposUsuarios);
        return usuario;
    }

    public CaspUsuarios buscarOuFalhar(Integer id) {
        CaspUsuarios usuario = usuariosRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Usuário não encontrado!")
        );
        if (!usuario.getITipoUsuario().getDescricaoTipoUsuario().equals("CLIENTE")) {
            throw new UsuarioRestritoException("O usuário não existe ou não é um cliente!");
        }
        return usuario;
    }

    @Transactional
    public CaspUsuarios novoCliente(CaspUsuarios input) {
        CaspUsuarios novoCliente = atribuiTipoCliente(input);
        return usuariosRepository.save(novoCliente);
    }

    @Transactional
    public CaspUsuarios editarCliente(CaspUsuarios input,CaspUsuarios usuarioAtual) {        
        input.setNlogin(usuarioAtual.getNlogin());
        input.setITipoUsuario(usuarioAtual.getITipoUsuario());
        input.setSenha(usuarioAtual.getSenha());
        CaspUsuarios edit = usuariosRepository.save(input);
        return edit;
    }

    @Transactional
    public void deletarCliente(Integer id) {
        buscarOuFalhar(id);
        usuariosRepository.deleteById(id);
    }

    public List<CaspUsuarios> listarClientes(String exibir) {
        String situacao = "";
        switch(exibir){
            case "ATIVOS":
                situacao = "A";
                break;
            case "INATIVOS":
                situacao = "I";
                break;
            default:
                situacao="";
                break;
        }
        return usuariosRepository.buscarClientes(situacao);
    }
    
    public List<CaspUsuarios> clientesPorFiltros(Integer entidade, Integer area, Integer sistema, String exibir) {
        String situacao;
        switch (exibir.toUpperCase()) {
            case "ATIVOS":
                situacao = "A";
                break;
            case "INATIVOS":
                situacao = "I";
                break;
            default:
                situacao = "TODOS";
                break;
        }
        return usuariosRepository.clientesPorFiltros(new CaspEntidades(entidade), entidade, new CaspAreas(area), area, new CaspSistemas(sistema), sistema, situacao);
    }

}

