package caspvale.caspsuporte.modulos.atendimento.domain.service;

import caspvale.caspsuporte.modulos.atendimento.common.OperacoesTexto;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspChamados;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspSituacoes;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.ChamadosRepository;
import caspvale.caspsuporte.modulos.atendimento.domain.repository.SituacoesRepository;
import caspvale.caspsuporte.modulos.atendimento.exception.EntidadeNaoEncontradaException;
import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import caspvale.caspsuporte.modulos.atendimento.exception.NegocioException;
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
        return gravar(popularNovoChamado(replaceHtmlStrings(chamado)));
    }

    @Transactional
    public CaspChamados editarChamado(CaspChamados chamadoEdicao) {
        CaspUsuarios usuarioLogado = permissoes.caspUsuarioLogado();
        CaspChamados chamadoAtual = chamadoLocalizadoPermitidoParaUsuario(chamadoEdicao.getIChamado(), usuarioLogado);
        if (!edicaoPermitida(chamadoAtual, usuarioLogado)) {
            throw new NegocioException("Edição não permitida para este chamado e usuário!");
        }
        return gravar(chamadoParaEdicao(replaceHtmlStrings(chamadoEdicao), chamadoAtual));
    }

    public boolean edicaoPermitida(CaspChamados chamado, CaspUsuarios usuario) {
        if (permissoes.roleANALISTA()) {
            permissoes.permissoesPorEntidadeAreaSistema(chamado, usuario);
            return Objects.equals(usuario.getIUsuario(), chamado.getIUsuarioAtendimento().getIUsuario());
        }
        if (permissoes.roleADMIN()) {
            return true;
        }
        if (permissoes.roleCLIENTE()) {
            return Objects.equals(usuario.getIUsuario(), chamado.getIUsuarioAbertura().getIUsuario());
        }
        return false;
    }

    private CaspChamados replaceHtmlStrings(CaspChamados caspChamado) {
        OperacoesTexto operacoesTexto = new OperacoesTexto();
        String resumo = operacoesTexto.replaceHtml(caspChamado.getResumoChamado());
        String descricao = operacoesTexto.replaceHtml(caspChamado.getDescricaoChamado());
        String problema = operacoesTexto.replaceHtml(caspChamado.getDescricaoProblema());
        String solucao = operacoesTexto.replaceHtml(caspChamado.getDescricaoSolucao());
        String historicoEmail = operacoesTexto.replaceHtml(caspChamado.getHistoricoEmail());
        String historicoChat = operacoesTexto.replaceHtml(caspChamado.getHostoricoChat());
        caspChamado.setResumoChamado(resumo);
        caspChamado.setDescricaoChamado(descricao);
        caspChamado.setDescricaoProblema(problema);
        caspChamado.setDescricaoSolucao(solucao);
        caspChamado.setHistoricoEmail(historicoEmail);
        caspChamado.setHostoricoChat(historicoChat);
        return caspChamado;
    }

    @Transactional
    public CaspChamados gravar(CaspChamados caspChamado) {
        caspChamado.setUsuarioAlteracao(permissoes.login());
        caspChamado.setDtAlteracao(LocalDateTime.now());
        return chamadosRepository.saveAndFlush(caspChamado);
    }

    @Transactional
    public void deletar(Integer id) {
        permissoes.exclusivoAdministrador();
        buscarOuFalhar(id);
        chamadosRepository.deleteById(id);
    }

    @Transactional
    public CaspChamados movimentarChamado(CaspChamados caspChamado, String acao, String comentario, CaspUsuarios usuarioLogado, String agenda, Integer usuarioEncaminhar) {

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
                caspChamado = cancelarChamado(caspChamado, usuarioLogado);
                break;
            case "encerrar":
                caspChamado = encerrarChamado(caspChamado, usuarioLogado);
                break;
            case "aguardarUsuario":
                caspChamado = aguardarUsuario(caspChamado, usuarioLogado);
                break;
            case "aguardarChamadoExterno":
                caspChamado = aguardarChamadoExterno(caspChamado, usuarioLogado);
                break;
            case "enviarEstudo":
                caspChamado = enviarEstudo(caspChamado, usuarioLogado);
                break;
            case "deixarAtendimento":
                caspChamado = deixarAtendimento(caspChamado, usuarioLogado);
                break;
            case "responder":
                caspChamado = responder(caspChamado, usuarioLogado);
                break;
            case "reabrir":
                caspChamado = reabrir(caspChamado, usuarioLogado);
                break;
            case "comentario":
                caspChamado = comentario(caspChamado, usuarioLogado);
                break;
            case "anexar":
                break;
            case "editar":
                permissoes.restritoAoCliente();
                permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
                break;
            default:
                throw new NegocioException("A ação informada [" + acao + "] não existe ou não é permitida.");
        }

        if (acaoPermitidaParaSituacaoDoChamado(caspChamado, acao)) {
            CaspChamados c = gravar(caspChamado);
        }
        OperacoesTexto operacoesTexto = new OperacoesTexto();

        if (acao.equals("comentario")) {
            if (operacoesTexto.textoVazio(comentario)) {
                throw new NegocioException("Não foi informado um comentário!");
            }
        }
        if (!operacoesTexto.textoVazio(comentario)) {
            anexosService.adicionarComentario(caspChamado, usuarioLogado, operacoesTexto.replaceHtml(comentario));
        }
        return caspChamado;//"Chamado "+caspChamado.getIChamado()+" atualizado!";
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
        if (usuarioEncaminhamento.getITipoUsuario().getDescricaoTipoUsuario().equals("CLIENTE")) {
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
                caspChamado.setIUsuarioAtendimento(null);
                caspChamado.setIUsuarioEncaminhamento(null);
                caspChamado.setIUsuarioEncerramento(null);
                return atualizaSituacaoDoChamado(caspChamado, 7);
            } else {
                throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
            }
        }

        if (permissoes.roleANALISTA()) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
            if (Objects.equals(caspChamado.getIUsuarioAtendimento().getIUsuario(), usuarioLogado.getIUsuario())
                    && caspChamado.getISituacao().getISituacao() == 3) {
                caspChamado.setIUsuarioAtendimento(null);
                caspChamado.setIUsuarioEncaminhamento(null);
                caspChamado.setIUsuarioEncerramento(null);
                return atualizaSituacaoDoChamado(caspChamado, 7);
            } else {
                throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
            }
        }

        if (permissoes.roleADMIN()) {
            caspChamado.setIUsuarioAtendimento(null);
            caspChamado.setIUsuarioEncaminhamento(null);
            caspChamado.setIUsuarioEncerramento(null);
            return atualizaSituacaoDoChamado(caspChamado, 7);
        } else {
            throw new NegocioException("Sem permissão para cancelar o chamado com a situação atual!");
        }
    }

    private CaspChamados encerrarChamado(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (caspChamado.getISituacao().getISituacao() != 3) {
            throw new NegocioException("Somente chamado EM ATENDIMENTO pode ser encerrado!");
        }
        String role = usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario();
        if (role.equals("ANALISTA")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
            if (!Objects.equals(caspChamado.getIUsuarioAtendimento().getIUsuario(), usuarioLogado.getIUsuario())) {
                throw new NegocioException("O chamado não pode ser encerrado, pois está em atendimento por outro analista!");
            }
        }
        caspChamado.setIUsuarioAtendimento(null);
        caspChamado.setIUsuarioEncaminhamento(null);
        caspChamado.setIUsuarioEncerramento(usuarioLogado);
        caspChamado.setDataEncerramento(LocalDateTime.now());
        return atualizaSituacaoDoChamado(caspChamado, 5);
    }

    private CaspChamados aguardarUsuario(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        return atualizaSituacaoDoChamado(caspChamado, 8);
    }

    private CaspChamados aguardarChamadoExterno(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        return atualizaSituacaoDoChamado(caspChamado, 9);
    }

    private CaspChamados enviarEstudo(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        return atualizaSituacaoDoChamado(caspChamado, 6);
    }

    private CaspChamados deixarAtendimento(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        caspChamado.setIUsuarioAtendimento(null);
        return atualizaSituacaoDoChamado(caspChamado, 1);
    }

    private CaspChamados responder(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.permissoesPorEntidadeAreaSistema(caspChamado, caspChamado.getIUsuarioAbertura());
        return atualizaSituacaoDoChamado(caspChamado, 3);
    }

    private CaspChamados reabrir(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        permissoes.restritoAoCliente();
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        caspChamado.setIUsuarioEncerramento(null);
        caspChamado.setIUsuarioAtendimento(null);
        return atualizaSituacaoDoChamado(caspChamado, 1);
    }

    private CaspChamados comentario(CaspChamados caspChamado, CaspUsuarios usuarioLogado) {
        if (!usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario().equals("ADMINISTRADOR")) {
            permissoes.permissoesPorEntidadeAreaSistema(caspChamado, usuarioLogado);
        }
        return caspChamado;
    }

    public List<CaspChamados> aguardando(CaspUsuarios usuarioLogado) {
        String login = usuarioLogado.getNlogin();
        switch (usuarioLogado.getITipoUsuario().getDescricaoTipoUsuario()) {
            case "ADMINISTRADOR":
                return chamadosRepository.aguardando();
            case "ANALISTA":
                return chamadosRepository.aguardandoAreaEntidadeSistema(login);
            case "CLIENTE":
                return chamadosRepository.aguardandoAreaEntidadeSistema(login);
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
                return chamadosRepository.aguardandoAreaEntidadeSistemaSize(login);
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
                return chamadosRepository.emAnaliseUsuario(usuarioLogado);
            //return chamadosRepository.emAnaliseAreaEntidadeSistema(login);
            case "CLIENTE":
                return chamadosRepository.emAnaliseUsuario(usuarioLogado);
            //return chamadosRepository.emAnaliseAreaEntidadeSistema(login);
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
                return chamadosRepository.emAnaliseUsuarioSize(usuarioLogado);
            case "CLIENTE":
                return chamadosRepository.emAnaliseUsuarioSize(usuarioLogado);
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
                return chamadosRepository.finalizadosAreaEntidadeSistema(login);
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
                return chamadosRepository.finalizadosAreaEntidadeSistemaSize(login);
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

    private CaspChamados chamadoParaEdicao(CaspChamados chamadoEdicao, CaspChamados chamadoAtual) {
        chamadoEdicao.setISituacao(chamadoAtual.getISituacao());
        chamadoEdicao.setSituacaoChamado(chamadoAtual.getSituacaoChamado());
        chamadoEdicao.setIUsuarioAbertura(chamadoAtual.getIUsuarioAbertura());
        chamadoEdicao.setIUsuarioAtendimento(chamadoAtual.getIUsuarioAtendimento());
        chamadoEdicao.setIUsuarioEncaminhamento(chamadoAtual.getIUsuarioEncaminhamento());
        chamadoEdicao.setIUsuarioEncerramento(chamadoEdicao.getIUsuarioEncerramento());
        chamadoEdicao.setDataAbertura(chamadoAtual.getDataAbertura());
        chamadoEdicao.setDataAgendamento(chamadoAtual.getDataAgendamento());
        chamadoEdicao.setDataAtendimento(chamadoAtual.getDataAtendimento());
        chamadoEdicao.setDataEncerramento(chamadoAtual.getDataEncerramento());
        return chamadoEdicao;
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

    private boolean acaoPermitidaParaSituacaoDoChamado(CaspChamados caspChamado, String acao) {
        String descricaoSituacaoAtual = caspChamado.getISituacao().getDescricaoSituacao();
        List<String> situacoesPermitidas = new ArrayList<>();
        switch (acao) {
            case "atender":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("CANCELADO");
                situacoesPermitidas.add("ENCERRADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "agendar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "encaminhar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "cancelar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "encerrar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("EM ATENDIMENTO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "aguardarUsuario":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("EM ESTUDO");
                situacoesPermitidas.add("ENCAMINHADO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "aguardarChamadoExterno":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "enviarEstudo":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "deixarAtendimento":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "responder":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "reabrir":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("ENCERRADO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "comentario":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("CANCELADO");
                situacoesPermitidas.add("ENCERRADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "anexar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("ENCERRADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            case "editar":
                situacoesPermitidas.clear();
                situacoesPermitidas.add("AGUARDANDO ATENDIMENTO");
                situacoesPermitidas.add("EM ATENDIMENTO");
                situacoesPermitidas.add("AGENDADO");
                situacoesPermitidas.add("AGUARDANDO USUÁRIO");
                situacoesPermitidas.add("AGUARDANDO CHAMADO EXTERNO");
                situacoesPermitidas.add("ENCAMINHADO");
                situacoesPermitidas.add("EM ESTUDO");
                return verificarSeContemSituacao(descricaoSituacaoAtual, situacoesPermitidas);
            default:
                throw new NegocioException("A ação informada [" + acao + "] não existe ou não é permitida.");
        }
    }

    private boolean verificarSeContemSituacao(String descricaoSituacaoAtual, List<String> situacoes) {
        for (String situacao : situacoes) {
            if (descricaoSituacaoAtual.contains(situacao)) {
                return true;
            }
        }
        return false;
    }
}
