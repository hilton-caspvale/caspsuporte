package caspvale.caspsuporte.atendimento.domain.service;

import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.atendimento.domain.model.CaspSituacoes;
import caspvale.caspsuporte.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.atendimento.domain.repository.ChamadosRepository;
import caspvale.caspsuporte.atendimento.domain.repository.SituacoesRepository;
import caspvale.caspsuporte.domain.exception.EntidadeNaoEncontradaException;
import caspvale.caspsuporte.domain.exception.UsuarioRestritoException;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import caspvale.caspsuporte.atendimento.domain.model.CaspAnexos;
import caspvale.caspsuporte.atendimento.domain.repository.AnexosRepository;
import caspvale.caspsuporte.atendimento.domain.repository.UsuariosRepository;
import caspvale.caspsuporte.domain.exception.NegocioException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hilton
 */
@Service
public class ChamadosService {

    private final ChamadosRepository chamadosRepository;
    private final Permissoes permissoes;
    private final SituacoesRepository situacoesRepository;
    private final AnexosService anexosService;
    private final UsuariosService usuariosService;

    public ChamadosService(ChamadosRepository chamadosRepository, Permissoes permissoes,
            SituacoesRepository situacoesRepository, AnexosService anexosService,
            UsuariosService usuariosService) {
        this.chamadosRepository = chamadosRepository;
        this.permissoes = permissoes;
        this.situacoesRepository = situacoesRepository;
        this.anexosService = anexosService;
        this.usuariosService = usuariosService;
    }

    public CaspChamados chamadoLocalizadoPermitidoParaUsuario(Integer id, CaspUsuarios caspUsuario) {
        CaspChamados chamadoLocalizado = chamadosRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Chamado não localizado!")
        );
        if (permissoes.roleADMIN()) {
            return chamadoLocalizado;
        }
        if (permissoes.roleCLIENTE()) {
            permissoes.permissoesPorEntidadeAreaSistema(chamadoLocalizado, caspUsuario);
        }
        if (permissoes.roleANALISTA()) {
            permissoes.permissoesPorEntidade(chamadoLocalizado, caspUsuario);
        }
        return chamadoLocalizado;
    }

    public CaspChamados buscarOuFalhar(Integer id) {
        return chamadosRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Chamado não localizado!")
        );
    }

    @Transactional
    public CaspChamados novoChamado(CaspChamados chamado) {
        return gravar(popularNovoChamado(chamado));
    }

    @Transactional
    public CaspChamados editar(CaspChamados input) {
        permissoes.exclusivoAdministrador();
        return chamadosRepository.save(input);
    }

    @Transactional
    private CaspChamados gravar(CaspChamados caspChamado) {
        caspChamado.setUsuarioAlteracao(permissoes.login());
        caspChamado.setDtAlteracao(LocalDateTime.now());
        return chamadosRepository.saveAndFlush(caspChamado);
    }

    @Transactional
    public void adicionarComentario(CaspChamados caspchamado, CaspUsuarios iUsuarioLogado, String comentario) {
        CaspAnexos caspAnexos = new CaspAnexos();
        caspAnexos.setIChamado(caspchamado);
        caspAnexos.setIUsuario(iUsuarioLogado);
        caspAnexos.setComentarioArquivo(comentario);
        caspAnexos.setDataArquivo(LocalDateTime.now());
        anexosService.gravar(caspAnexos);
    }

    @Transactional
    public void deletar(Integer id) {
        permissoes.exclusivoAdministrador();
        buscarOuFalhar(id);
        chamadosRepository.deleteById(id);
    }

    @Transactional
    public String movimentarChamado(CaspChamados caspChamado, String acao, String mensagem, CaspUsuarios usuarioLogado, String agenda, Integer usuarioEncaminhar) {
        CaspSituacoes caspSituacoes = caspChamado.getISituacao();
        switch (acao) {
            case "atender":
                caspChamado = atenderChamado(caspChamado, usuarioLogado);
                break;
            case "agendar":
                caspChamado = agendarChamado(caspChamado, agenda);
                break;
            case "encaminhar":
                caspChamado = encaminharChamado(caspChamado, usuarioEncaminhar);
                break;
            case "cancelar":
                caspSituacoes = situacao(7);
                break;
            case "encerrar":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(5);
                caspChamado.setIUsuarioEncerramento(usuarioLogado);
                break;
            case "aguardarUsuario":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(8);
                break;
            case "aguardarChamadoExterno":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(9);
                break;
            case "enviarEstudo":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(6);
                break;
            case "deixarAtendimento":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(1);
                caspChamado.setIUsuarioAtendimento(null);
                break;
            case "responder":
                caspSituacoes = situacao(3);
                break;
            case "reabrir":
                permissoes.restritoAoCliente();
                caspSituacoes = situacao(1);
                break;
            case "comentario":
                break;
            case "anexar":
                break;
            case "editar":
                permissoes.restritoAoCliente();
                break;
            default:
                throw new NegocioException("A ação informada [" + acao + "] não existe ou não é permitida.");
        }

        if (mensagem != null) {
            adicionarComentario(caspChamado, usuarioLogado, mensagem);
        }

        caspChamado.setISituacao(caspSituacoes);
        caspChamado.setSituacaoChamado(caspSituacoes.getDescricaoSituacao());
        return mensagem;
    }

    private CaspChamados atenderChamado(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        caspChamado = atualizaSituacaoDoChamado(caspChamado, 3);
        caspChamado.setIUsuarioAtendimento(usuarioLogado);
        caspChamado.setDataAtendimento(LocalDateTime.now());
        caspChamado.setIUsuarioEncaminhamento(null);
        caspChamado.setDataAgendamento(null);
        caspChamado.setDataEncerramento(null);
        return caspChamado;
    }

    private CaspChamados agendarChamado(CaspChamados caspChamado, String agenda) {
        if (agenda != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(agenda, formatter);
                caspChamado.setDataAgendamento(dateTime);
                caspChamado = atualizaSituacaoDoChamado(caspChamado, 2);
            } catch (Exception e) {
                throw new NegocioException("A data do agendamento está inválida[" + agenda + "]! ");
            }
        } else {
            throw new NegocioException("Nenhumda data informada para o agendamento.");
        }
        return caspChamado;
    }

    private CaspChamados encaminharChamado(CaspChamados caspChamado, Integer iUsuarioEncaminhamento) {
        if (iUsuarioEncaminhamento == null) {
            throw new NegocioException("Usuário do encaminhamento não informado corretamente.");
        }
        CaspUsuarios usuarioEncaminhamento = usuariosService.buscarOuFalhar(iUsuarioEncaminhamento);
        if (usuarioEncaminhamento.getITipoUsuario() == null
                || usuarioEncaminhamento.getITipoUsuario().getDescricaoTipoUsuario().equals("CLIENTE")) {
            throw new NegocioException("O chamado só pode ser encaminhado para um analista ou para o administrador!");
        }
        caspChamado = atualizaSituacaoDoChamado(caspChamado, 4);
        caspChamado.setIUsuarioEncaminhamento(usuarioEncaminhamento);
        caspChamado.setIUsuarioAtendimento(null);
        return caspChamado;
    }

    public CaspChamados cancelarChamado(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        if (permissoes.roleCLIENTE()) {
            if (Objects.equals(caspChamado.getIUsuarioAbertura().getIUsuario(), usuarioLogado.getIUsuario())
                    && caspChamado.getISituacao().getISituacao() == 1) {
                return atualizaSituacaoDoChamado(caspChamado, 7);
            } else {
                throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
            }
        }

        if (permissoes.roleANALISTA()) {
            if (Objects.equals(caspChamado.getIUsuarioAtendimento().getIUsuario(), usuarioLogado.getIUsuario())
                    && caspChamado.getISituacao().getISituacao() == 3) {
                return atualizaSituacaoDoChamado(caspChamado, 7);
            } else {
                throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
            }
        }

        if (permissoes.roleADMIN()) {
            return atualizaSituacaoDoChamado(caspChamado, 7);
        } else {
            throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
        }

    }

    public List<CaspChamados> aguardando(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.aguardando();
            case "ANALISTA":
                return chamadosRepository.aguardandoAreaEntidadeSistema(login);
            case "CLIENTE":
                return chamadosRepository.aguardandoClienteUsuario(login);
            default:
                return new ArrayList<>();
        }
    }

    public Integer aguardandoSize(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.aguardandoSize();
            case "ANALISTA":
                return chamadosRepository.aguardandoAreaEntidadeSistemaSize(login);
            case "CLIENTE":
                return chamadosRepository.aguardandoClienteUsuarioSize(login);
            default:
                return 0;
        }
    }

    public List<CaspChamados> emAnalise(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.emAnalise();
            case "ANALISTA":
                return chamadosRepository.emAnaliseAreaEntidadeSistema(login);
            case "CLIENTE":
                return chamadosRepository.emAnaliseClienteUsuario(login);
            default:
                return new ArrayList<>();
        }
    }

    public Integer emAnaliseSize(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.emAnaliseSize();
            case "ANALISTA":
                return chamadosRepository.emAnaliseAreaEntidadeSistemaSize(login);
            case "CLIENTE":
                return chamadosRepository.emAnaliseClienteUsuarioSize(login);
            default:
                return 0;
        }
    }

    public List<CaspChamados> finalizados(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.finalizados();
            case "ANALISTA":
                return chamadosRepository.finalizadosAreaEntidadeSistema(login);
            case "CLIENTE":
                return chamadosRepository.finalizadosClienteUsuario(login);
            default:
                return new ArrayList<>();
        }
    }

    public Integer finalizadosSize(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.finalizadosSize();
            case "ANALISTA":
                return chamadosRepository.finalizadosAreaEntidadeSistemaSize(login);
            case "CLIENTE":
                return chamadosRepository.finalizadosClienteUsuarioSize(login);
            default:
                return 0;
        }
    }

    public List<CaspChamados> listar() {
        permissoes.restritoAoCliente();
        return chamadosRepository.findAll();
    }

    private CaspChamados popularNovoChamado(CaspChamados chamado) {
        CaspSituacoes situacao = situacao(1);
        chamado.setIChamado(null);
        chamado.setISituacao(situacao);
        chamado.setDataAbertura(LocalDateTime.now());
        chamado.setSituacaoChamado(situacao.getDescricaoSituacao());
        return chamado;
    }

    /**
     * 1-AGUARDANDO_ATENDIMENTO 2-AGENDADO 8-AGUARDANDO USUÁRIO 9-AGUARDANDO
     * CHAMADO EXTERNO 7-CANCELADO 5-ENCERRADO 3-EM ATENDIMENTO 4-ENCAMINHADO
     * 6-EM ESTUDO
     *
     * @param iSituacao código da situação.
     */
    private CaspSituacoes situacao(Integer iSituacao) {
        return situacoesRepository.findById(iSituacao).get();
    }

    /**
     * 1-AGUARDANDO_ATENDIMENTO 2-AGENDADO 8-AGUARDANDO USUÁRIO 9-AGUARDANDO
     * CHAMADO EXTERNO 7-CANCELADO 5-ENCERRADO 3-EM ATENDIMENTO 4-ENCAMINHADO
     * 6-EM ESTUDO
     *
     * @param chamado chamado
     * @param iSituacao código da situação.
     */
    private CaspChamados atualizaSituacaoDoChamado(CaspChamados chamado, Integer iSituacao) {
        CaspSituacoes situacaoChamado = situacao(iSituacao);
        chamado.setISituacao(situacaoChamado);
        chamado.setSituacaoChamado(situacaoChamado.getDescricaoSituacao());
        return chamado;
    }

}
