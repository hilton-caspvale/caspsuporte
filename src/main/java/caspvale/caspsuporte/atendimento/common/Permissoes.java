package caspvale.caspsuporte.atendimento.common;

import caspvale.caspsuporte.atendimento.api.assembler.ChamadosAssembler;
import caspvale.caspsuporte.atendimento.api.assembler.UsuariosAssembler;
import caspvale.caspsuporte.atendimento.api.model.AreasInputModel;
import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.api.model.EntidadesInputModel;
import caspvale.caspsuporte.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.atendimento.api.model.SistemasInputModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.atendimento.domain.model.CaspSistemas;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.repository.UsuariosRepository;
import caspvale.caspsuporte.domain.exception.UsuarioRestritoException;
import caspvale.caspsuporte.domain.security.DetalheUser;
import caspvale.caspsuporte.domain.security.SSUserDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Hilton
 */
@Configuration
public class Permissoes {

//    public boolean SOMENTE_ENTIDADES_DO_USUARIO = true;
//    public boolean SOMENTE_AREAS_DO_USUARIO = true;
//    public boolean SOMENTE_SISTEMAS_DO_USUARIO = true;

    @Autowired
    public UsuariosAssembler usuariosAssembler;
    @Autowired
    public ChamadosAssembler chamadosAssembler;
    @Autowired
    public UsuariosRepository usuariosRepository;

    public SecurityContext context() {
        return SecurityContextHolder.getContext();
    }

    public String login() {
        String login = context().getAuthentication().getName();
        return login;
    }

    public String role() {
        String role = context().getAuthentication().getAuthorities().toString().replace("[", "").replace("]", "");
        return role;
    }

    public ChamadosModel caspChamadosToModel(CaspChamados caspChamado) {
        return chamadosAssembler.toModel(caspChamado);
    }

    public CaspChamados chamadoModelToEntity(ChamadosModel chamadoModel) {
        return chamadosAssembler.toEntity(chamadoModel);
    }

    public boolean roleCLIENTE() {
        return role().equals("CLIENTE");
    }

    public boolean roleANALISTA() {
        return role().equals("ANALISTA");
    }

    public boolean roleADMIN() {
        return role().equals("ADMINISTRADOR");
    }

    public void exclusivoAnalista() {
        if (!roleANALISTA()) {
            throw new UsuarioRestritoException();
        }
    }

    public void exclusivoAdministrador() {
        if (!roleADMIN()) {
            throw new UsuarioRestritoException();
        }
    }

    public void exclusivoCliente() {
        if (!roleCLIENTE()) {
            throw new UsuarioRestritoException();
        }
    }

    public void restritoAoCliente() {
        if (!roleANALISTA() && !roleADMIN()) {
            throw new UsuarioRestritoException();
        }
    }

    private boolean permissaoPorEntidade(ChamadosModel chamado, UsuariosModel usuarioLogadoModel) {
        List<EntidadesInputModel> entidadesDoUsuario = usuarioLogadoModel.getCaspEntidadesList();
        for (EntidadesInputModel entidadeChamado : chamado.getCaspEntidadesList()) {
            if (entidadesDoUsuario.contains(entidadeChamado)) {
                return true;
            }
        }
        throw new UsuarioRestritoException("O usuário não tem permissão para nenhuma das entidades no chamado!");
    }

    private boolean permissaoPorArea(ChamadosModel chamado, UsuariosModel usuarioLogadoModel) {
        List<AreasInputModel> areasDoUsuario = usuarioLogadoModel.getCaspAreasList();
        for (AreasInputModel areaDoChamado : chamado.getCaspAreasList()) {
            if (areasDoUsuario.contains(areaDoChamado)) {
                return true;
            }
        }
        throw new UsuarioRestritoException("O usuário não tem permissão para nenhuma das áreas no chamado!");
    }

    private boolean permissaoPorSistema(ChamadosModel chamado, UsuariosModel usuarioLogadoModel) {
        List<SistemasInputModel> sistemasDoUsuario = usuarioLogadoModel.getCaspSistemasList();
        for (SistemasInputModel sistemaDoChamado : chamado.getCaspSistemasList()) {
            if (sistemasDoUsuario.contains(sistemaDoChamado)) {
                return true;
            }
        }
        throw new UsuarioRestritoException("O usuário não tem permissão para nenhum dos sistemas no chamado!");
    }

    public UsuariosModel usuarioLogadoModel() {
        return usuariosAssembler.toModel(caspUsuarioLogado());
    }

    public CaspUsuarios caspUsuarioLogado() {
        return usuariosRepository.findByNlogin(login()).orElseThrow();
    }

    public void permissoesAberturaUsuarioLogado(ChamadosModel chamado) {
        UsuariosModel usuarioLogadoModel = usuarioLogadoModel();
        permissaoPorEntidade(chamado, usuarioLogadoModel);
        permissaoPorArea(chamado, usuarioLogadoModel);
        permissaoPorSistema(chamado, usuarioLogadoModel);
    }

    public void permissoesPorEntidadeAreaSistema(CaspChamados caspChamado, CaspUsuarios caspUsuario) {
        ChamadosModel chamadoModel = chamadosAssembler.toModel(caspChamado);
        UsuariosModel usuarioModel = usuariosAssembler.toModel(caspUsuario);
        permissaoPorEntidade(chamadoModel, usuarioModel);
        permissaoPorArea(chamadoModel, usuarioModel);
        permissaoPorSistema(chamadoModel, usuarioModel);
    }

    public void permissoesPorEntidade(CaspChamados caspChamado, CaspUsuarios caspUsuario) {
        ChamadosModel chamadoModel = chamadosAssembler.toModel(caspChamado);
        UsuariosModel usuarioModel = usuariosAssembler.toModel(caspUsuario);
        permissaoPorEntidade(chamadoModel, usuarioModel);
    }

}
