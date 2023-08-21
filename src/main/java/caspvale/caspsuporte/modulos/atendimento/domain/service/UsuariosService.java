package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.UsuariosRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
import caspvale.caspsuporte.modulos.atendimento.exception.UsuarioRestritoException;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import java.util.List;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final Permissoes permissoes;

    public UsuariosService(UsuariosRepository usuariosRepository, Permissoes permissoes) {
        this.usuariosRepository = usuariosRepository;
        this.permissoes = permissoes;
    }

    public CaspUsuarios buscarOuFalhar(Integer id) {
        return usuariosRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Usuário não localizado!")
        );
    }

    @Transactional
    public CaspUsuarios novoUsuario(CaspUsuarios input) {
        if (usuarioCliente(input)) {
            throw new UsuarioRestritoException("Usuário CLIENTE não pode ser criado por esse recurso!");
        }
        return usuariosRepository.save(input);
    }

    @Transactional
    public CaspUsuarios editarUsuario(CaspUsuarios input, CaspUsuarios usuarioAtual) {
        input.setNlogin(usuarioAtual.getNlogin());
        if (usuarioCliente(input) || usuarioCliente(usuarioAtual)) {
            throw new UsuarioRestritoException("Usuário CLIENTE não pode ser alterado por esse recurso!");
        }
        if (!permissoes.roleADMIN()) {
            input.setITipoUsuario(usuarioAtual.getITipoUsuario());
        }
        input.setSenha(usuarioAtual.getSenha());
        return usuariosRepository.save(input);
    }

    @Transactional
    public void deletar(Integer id) {
        if (usuarioCliente(buscarOuFalhar(id))) {
            throw new UsuarioRestritoException("Usuário CLIENTE não pode ser alterado por esse recurso!");
        }
        usuariosRepository.deleteById(id);
    }

    @Transactional
    public boolean trocarSenha(CaspUsuarios usuario, String senha) {
        switch (permissoes.role()) {
            case "ADMINISTRADOR":
                usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
                break;
            case "ANALISTA":
                if (usuarioCliente(usuario) || usuario.getNlogin().equals(permissoes.login())) {
                    usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
                } else {
                    throw new UsuarioRestritoException("Sem permissão para alterar a senha deste usuário!");
                }
                break;
            default:
                if (usuario.getNlogin().equals(permissoes.login())) {
                    usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
                } else {
                    throw new UsuarioRestritoException("Sem permissão para alterar a senha deste usuário!");
                }
        }

        try {
            usuariosRepository.saveAndFlush(usuario);
            return true;
        } catch (Exception e) {
            throw new NegocioException("Senha não alterada!");
        }
    }

    @Transactional
    public CaspUsuarios atualizarPerfil(CaspUsuarios usuarioAtual, CaspUsuarios usuarioAtualizado) {
        if (!Objects.equals(usuarioAtual.getIUsuario(), usuarioAtualizado.getIUsuario())) {
            throw new UsuarioRestritoException("Sem permissão para alterar o perfil de outro usuário!");
        }
        usuarioAtual.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioAtual.setContatoUsuario(usuarioAtualizado.getContatoUsuario());
        usuarioAtual.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
        return usuariosRepository.saveAndFlush(usuarioAtual);
    }

    public List<CaspUsuarios> listarClientes(String situacao) {
        return usuariosRepository.buscarClientes(situacao);
    }

    public CaspUsuarios buscarUsuarioPorLogin(String nlogin) {
        return usuariosRepository.findByNlogin(nlogin).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Usuário não localizado!"));
    }

    public List<CaspUsuarios> usuariosPorFilstros(Integer entidade, Integer area, Integer sistema, String exibir) {
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
        return usuariosRepository.usuariosPorFiltros(new CaspEntidades(entidade), entidade, new CaspAreas(area), area, new CaspSistemas(sistema), sistema, situacao);
    }

    public List<CaspUsuarios> usuariosAbertura(boolean somenteClientes, Integer entidade, Integer area, Integer sistema, String exibir) {
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
        return usuariosRepository.usuariosAbertura(somenteClientes, new CaspEntidades(entidade), entidade, new CaspAreas(area), area, new CaspSistemas(sistema), sistema, situacao);
    }

    public boolean usuarioCliente(CaspUsuarios usuario) {
        return usuario.getITipoUsuario().getITipoUsuario() == 3;
    }

    public boolean naoCliente(CaspUsuarios usuario) {
        return usuario.getITipoUsuario().getITipoUsuario() != 3;
    }

}
