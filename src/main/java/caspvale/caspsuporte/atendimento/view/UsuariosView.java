package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.atendimento.api.controller.TiposUsuariosController;
import caspvale.caspsuporte.atendimento.api.controller.UsuariosController;
import caspvale.caspsuporte.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.atendimento.api.model.TiposUsuariosModel;
import caspvale.caspsuporte.atendimento.api.model.UsuariosModel;
import caspvale.caspsuporte.atendimento.view.common.ViewObjects;
import caspvale.caspsuporte.atendimento.common.Rotas;
import caspvale.caspsuporte.atendimento.common.Permissoes;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/mv/atendimento")
public class UsuariosView {

    private final UsuariosController usuariosController;
    private final SistemasController sistemasController;
    private final AreasController areasController;
    private final EntidadesController entidadesController;
    private final TiposUsuariosController tiposUsuariosController;
    private final Rotas rotas;
    private final Permissoes permissoes;
    private final ViewObjects viewObjects;

    public UsuariosView(UsuariosController usuariosController, SistemasController sitemasController,
            EntidadesController entidadesController, AreasController areasController,
            TiposUsuariosController tiposUsuariosController, Permissoes permissoes, Rotas rotas, ViewObjects viewObjects) {
        this.usuariosController = usuariosController;
        this.sistemasController = sitemasController;
        this.areasController = areasController;
        this.entidadesController = entidadesController;
        this.tiposUsuariosController = tiposUsuariosController;
        this.permissoes = permissoes;
        this.rotas = rotas;
        this.viewObjects = viewObjects;
    }

    @GetMapping("usuario")
    public ModelAndView pageUsuario() {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/atendimento/pageUsuario");
        viewObjects.listaEntidades(mv, entidadesController);
        viewObjects.listaAreas(mv, areasController);
        viewObjects.listaSistemas(mv, sistemasController);
        mv.addObject("exibir", new String[]{"Todos", "Ativos", "Inativos"});
        return mv;
    }

    @GetMapping("conteudoModalUsuarios")
    public ModelAndView conteudoModalEdicaoUsuarios(@Valid @RequestParam(value = "id", required = false) Integer id,
            @Valid @RequestParam(value = "abertura", required = false) String abertura) {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_USUARIO);
            mv.addObject("usuarioModel", new UsuariosModel());
        } else {
            if (abertura == null) {
                mv.setViewName(rotas.EDITAR_USUARIO);
            } else {
                mv.setViewName(rotas.EDITAR_USUARIO_ABERTURA);
            }
            mv.addObject("usuarioModel", (UsuariosModel) usuariosController.usuario(id).getBody());
        }

        mv.addObject("listaSistemas", (List<SistemasModel>) sistemasController.listar().getBody());
        mv.addObject("listaAreas", (List<AreasModel>) areasController.listar().getBody());
        mv.addObject("listaEntidades", (List<EntidadesModel>) entidadesController.listar().getBody());
        mv.addObject("listaTipos", (List<TiposUsuariosModel>) tiposUsuariosController.naoClientes().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

    @GetMapping("trocarSenha")
    public ModelAndView trocarSenha(@Valid @RequestParam(value = "id", required = false) Integer id) {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView();
        mv.setViewName(rotas.TROCAR_SENHA);

        switch (permissoes.role()) {
            case "ADMINISTRADOR":
                mv.addObject("usuarioModel", usuariosController.usuario(id).getBody());
                break;
            case "ANALISTA":
                mv.addObject("usuarioModel", (UsuariosModel) usuariosController.usuario(id).getBody());
                break;
            case "CLIENTE":
                mv.addObject("usuarioModel", (UsuariosModel) usuariosController.buscarPorLogin(permissoes.login()).getBody());
                break;
            default:
                mv.addObject("usuarioModel", (UsuariosModel) usuariosController.buscarPorLogin(permissoes.login()).getBody());
                break;
        }
        return mv;
    }

}
