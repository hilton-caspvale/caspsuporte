package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.atendimento.api.controller.ChamadosController;
import caspvale.caspsuporte.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.atendimento.api.controller.NiveisController;
import caspvale.caspsuporte.atendimento.api.controller.OrigensController;
import caspvale.caspsuporte.atendimento.api.controller.PrioridadesController;
import caspvale.caspsuporte.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.atendimento.api.controller.UsuariosController;
import caspvale.caspsuporte.atendimento.api.model.AnexosInputModel;
import caspvale.caspsuporte.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.atendimento.api.model.ChamadosInputModel;
import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.atendimento.api.model.NiveisModel;
import caspvale.caspsuporte.atendimento.api.model.OrigensModel;
import caspvale.caspsuporte.atendimento.api.model.PrioridadesModel;
import caspvale.caspsuporte.atendimento.api.model.ProblemasInputModel;
import caspvale.caspsuporte.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosAberturaModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import caspvale.caspsuporte.atendimento.view.common.ViewObjects;
import caspvale.caspsuporte.atendimento.common.Rotas;
import caspvale.caspsuporte.domain.exception.UsuarioRestritoException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/mv/atendimento")
public class ChamadosView {
    
    private final Rotas rotas;
    private final ChamadosController chamadosController;
    private final UsuariosController usuariosController;
    private final EntidadesController entidadesController;
    private final AreasController areasController;
    private final SistemasController sistemasController;
    private final OrigensController origensController;
    private final NiveisController niveisController;
    private final PrioridadesController prioridadesController;
    private final ViewObjects viewObjects;
    private final Permissoes permissoes;
    
    public ChamadosView(Rotas rotas, ChamadosController chamadosController, EntidadesController entidadesController,
            AreasController areasController, UsuariosController usuariosController,
            SistemasController sistemasController, OrigensController origensController, NiveisController niveisController,
            PrioridadesController prioridadesController, ViewObjects viewObjects, Permissoes permissoes) {
        this.rotas = rotas;
        this.chamadosController = chamadosController;
        this.entidadesController = entidadesController;
        this.areasController = areasController;
        this.usuariosController = usuariosController;
        this.sistemasController = sistemasController;
        this.origensController = origensController;
        this.niveisController = niveisController;
        this.prioridadesController = prioridadesController;
        this.permissoes = permissoes;
        this.viewObjects = viewObjects;
    }
    
    @GetMapping("chamado/{iChamado}")
    public ModelAndView verChamado(@Valid @PathVariable Integer iChamado) {
        String login = rotas.login();
        ChamadosModel chamado = (ChamadosModel) chamadosController.get(iChamado).getBody();
        int quantidadeComentarios = 0;
        int quantidadeArquivos = 0;
        for (AnexosInputModel anexo : chamado.getCaspAnexosList()) {
            if (anexo.getIArquivo() == null) {
                quantidadeComentarios++;
            } else {
                quantidadeArquivos++;
            }
        }
        ModelAndView mv = new ModelAndView(rotas.VER_CHAMADO);
        mv.addObject("caspChamados", chamado);
        mv.addObject("login", login);
        mv.addObject("quantidadeComentarios", quantidadeComentarios);
        mv.addObject("quantidadeArquivos", quantidadeArquivos);
        mv.addObject("areasEncaminhar", areasController.minhasAreas("A").getBody());
        
        List<Integer> listaIAreas = new ArrayList<>();
        chamado.getCaspAreasList().forEach(area -> {
            listaIAreas.add(area.getIArea());
        });
        mv.addObject("areachamado", areasController.areasComUsuariosAnalistasDoChamado(listaIAreas));
        return mvComAcoes(chamado, permissoes.usuarioLogadoModel(), mv);
    }
    
    @GetMapping("modal-selecionar-usuario")
    public ModelAndView modalSelecionarUsuario() {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView(rotas.MODAL_SELECIONAR_USUARIO);
        viewObjects.listaEntidades(mv, entidadesController);
        viewObjects.listaAreas(mv, areasController);
        viewObjects.listaSistemas(mv, sistemasController);
        mv.addObject("exibir", new String[]{"Ativos", "Inativos", "Todos"});
        return mv;
    }
    
    @GetMapping("selecionar-usuario")
    public ModelAndView selecionarUsuario(@RequestParam(value = "entidades", required = false, defaultValue = "0") int entidades,
            @RequestParam(value = "areas", required = false, defaultValue = "0") int areas,
            @RequestParam(value = "sistemas", required = false, defaultValue = "0") int sistemas,
            @RequestParam(value = "exibir", required = false, defaultValue = "false") String exibir,
            @RequestParam(value = "somenteClientes", required = false, defaultValue = "false") boolean somenteClientes) {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView(rotas.SELECIONAR_USUARIO);
        List<UsuariosAberturaModel> usuariosAberturaModel = (List<UsuariosAberturaModel>) usuariosController.listarUsuariosAbertura(entidades, areas, sistemas, exibir, somenteClientes).getBody();
        mv.addObject("usuariosAbertura", usuariosAberturaModel);
        return mv;
    }
    
    @GetMapping("chamado")
    public ModelAndView pageChamado(@RequestParam(value = "user", required = true) String user, RedirectAttributes attributes) {
        rotas.viewsDaRota();        
        ModelAndView mv = new ModelAndView(rotas.PAGINA_CHAMADO);
        String loginPermitido = loginPermitido(user);
        List<EntidadesModel> entidadesDoUsuario = new ArrayList<>();
        List<SistemasModel> sistemasDoUsuario = new ArrayList<>();
        List<AreasModel> areasDoUsuario = new ArrayList<>();
        if (rotas.roleADMIN() || rotas.roleANALISTA()) {
            entidadesDoUsuario = (List<EntidadesModel>) entidadesController.entidadesDoUsuario("A", loginPermitido).getBody();
            sistemasDoUsuario = (List<SistemasModel>) sistemasController.sistemasDoUsuario(loginPermitido, "A").getBody();
            areasDoUsuario = (List<AreasModel>) areasController.areasDoUsuario("A", loginPermitido).getBody();
        } else {
            entidadesDoUsuario = (List<EntidadesModel>) entidadesController.minhasEntidades("A").getBody();
            sistemasDoUsuario = (List<SistemasModel>) sistemasController.meusSistemas("A").getBody();
            areasDoUsuario = (List<AreasModel>) areasController.minhasAreas("A").getBody();
        }
        
        List<ProblemasInputModel> problemas = new ArrayList<>();
        
        sistemasDoUsuario.forEach(sistema -> {
            sistema.getCaspProblemasList().forEach(problema -> {
                if (!problemas.contains(problema)) {
                    problemas.add(problema);
                }
            });
        });
        ChamadosInputModel chamadoInput = novoChamado(loginPermitido);
        mv.addObject("role", rotas.role());
        mv.addObject("login", rotas.login());
        mv.addObject("chamado", chamadoInput);
        mv.addObject("entidadesDoUsuario", entidadesDoUsuario);
        mv.addObject("sistemasDoUsuario", sistemasDoUsuario);
        mv.addObject("areasDoUsuario", areasDoUsuario);
        mv.addObject("problemas", problemas);
        mv.addObject("origens", (List<OrigensModel>) origensController.listar().getBody());
        mv.addObject("niveis", (List<NiveisModel>) niveisController.listar().getBody());
        mv.addObject("prioridades", (List<PrioridadesModel>) prioridadesController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
    
    private ChamadosInputModel novoChamado(String nlogin) {
        UsuariosModel usuario = new UsuariosModel();
        if (rotas.role().equals("ADMINISTRADOR") || rotas.role().equals("ANALISTA")) {
            usuario = (UsuariosModel) usuariosController.buscarPorLogin(loginPermitido(nlogin)).getBody();
        } else {
            usuario = permissoes.usuarioLogadoModel();
        }
        ChamadosInputModel chamado = new ChamadosInputModel();
        chamado.setIusuarioAbertura(usuario.getIUsuario());
        chamado.setNlogin(usuario.getNlogin());
        chamado.setNomeUsuario(usuario.getNomeUsuario());
        chamado.setContatoSolicitante(usuario.getContatoUsuario());
        chamado.setEmailSolicitante(usuario.getEmailUsuario());
        return chamado;
    }
    
    
    @GetMapping("edicaoChamado")
    public ModelAndView pageEdicaoChamado(@RequestParam(value = "iChamado", required = true) Integer iChamado) {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView(rotas.PAGINA_EDICAO_CHAMADO);
        List<EntidadesModel> entidadesDoUsuario = new ArrayList<>();
        List<SistemasModel> sistemasDoUsuario = new ArrayList<>();
        List<AreasModel> areasDoUsuario = new ArrayList<>();
        ChamadosModel chamadosModel = (ChamadosModel) chamadosController.get(iChamado).getBody();
        String loginPermitido = chamadosModel.getIUsuarioAbertura().getNlogin();
        
        if (rotas.roleADMIN() || rotas.roleANALISTA()) {
            entidadesDoUsuario = (List<EntidadesModel>) entidadesController.entidadesDoUsuario("A", loginPermitido).getBody();
            sistemasDoUsuario = (List<SistemasModel>) sistemasController.sistemasDoUsuario(loginPermitido, "A").getBody();
            areasDoUsuario = (List<AreasModel>) areasController.areasDoUsuario("A", loginPermitido).getBody();
        } else {
            entidadesDoUsuario = (List<EntidadesModel>) entidadesController.minhasEntidades("A").getBody();
            sistemasDoUsuario = (List<SistemasModel>) sistemasController.meusSistemas("A").getBody();
            areasDoUsuario = (List<AreasModel>) areasController.minhasAreas("A").getBody();
        }
        
        List<ProblemasInputModel> problemas = new ArrayList<>();
        
        sistemasDoUsuario.forEach(sistema -> {
            sistema.getCaspProblemasList().forEach(problema -> {
                if (!problemas.contains(problema)) {
                    problemas.add(problema);
                }
            });
        });
        mv.addObject("role", rotas.role());
        mv.addObject("login", rotas.login());
        mv.addObject("chamado", chamadosModel);
        mv.addObject("entidadesDoUsuario", entidadesDoUsuario);
        mv.addObject("sistemasDoUsuario", sistemasDoUsuario);
        mv.addObject("areasDoUsuario", areasDoUsuario);
        mv.addObject("problemas", problemas);
        mv.addObject("origens", (List<OrigensModel>) origensController.listar().getBody());
        mv.addObject("niveis", (List<NiveisModel>) niveisController.listar().getBody());
        mv.addObject("prioridades", (List<PrioridadesModel>) prioridadesController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
    
    private String loginPermitido(String login) {
        switch (rotas.role()) {
            case "ADMINISTRADOR":
                return login;
            case "ANALISTA":
                return login;
            case "CLIENTE":
                return rotas.login();
            default:
                throw new UsuarioRestritoException();
        }
    }
    
    private ModelAndView mvComAcoes(ChamadosModel chamado, UsuariosModel usuarioLogado, ModelAndView mv) {
        if (chamado.getISituacao() != null) {
            String role = rotas.role();
            switch (chamado.getISituacao().getDescricaoSituacao()) {
                case "AGUARDANDO ATENDIMENTO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("agendar", "Agendar");
                        mv.addObject("encaminhar", "Encaminhar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("cancelar", "Cancelar");
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("agendar", "Agendar");
                            mv.addObject("encaminhar", "Encaminhar");
                            mv.addObject("editar", "Editar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    
                    break;
                case "EM ATENDIMENTO":
                    if (role.equals("ANALISTA")) {
                        if (chamado.getIUsuarioAtendimento().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("encerrar", "Encerrar");
                            mv.addObject("cancelar", "Cancelar");
                            mv.addObject("agendar", "Agendar");
                            mv.addObject("encaminhar", "Encaminhar");
                            mv.addObject("aguardarUsuario", "Aguardar usuário");
                            mv.addObject("aguardarChamadoExterno", "Aguardar chamado externo");
                            mv.addObject("enviarEstudo", "Enviar para estudo");
                            mv.addObject("deixarAtendimento", "Deixar o atendimento");
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("editar", "Editar");
                            //9
                            mv.addObject("comentario", "Comentário");
                        } else {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("agendar", "Agendar");
                            mv.addObject("encaminhar", "Encaminhar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "ENCAMINHADO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "AGUARDANDO CHAMADO EXTERNO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "AGUARDANDO USUÁRIO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("responder", "Responder");
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "EM ESTUDO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "AGENDADO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("atender", "Atender");
                        mv.addObject("anexar", "Anexar");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("anexar", "Anexar");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "ENCERRADO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("reabrir", "Reabrir");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("reabrir", "Reabrir");
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                case "CANCELADO":
                    if (role.equals("ANALISTA")) {
                        mv.addObject("reabrir", "Reabrir");
                        mv.addObject("comentario", "Comentário");
                    } else {
                        if (chamado.getIUsuarioAbertura().getIUsuario() == usuarioLogado.getIUsuario()) {
                            mv.addObject("comentario", "Comentário");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return mv;
    }
}
