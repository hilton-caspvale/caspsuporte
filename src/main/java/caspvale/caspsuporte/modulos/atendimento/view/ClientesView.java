package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.UsuariosClientesController;
import caspvale.caspsuporte.modulos.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.UsuariosClientesModel;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
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
public class ClientesView {

    private final UsuariosClientesController clientesController;
    private final SistemasController sistemasController;
    private final AreasController areasController;
    private final EntidadesController entidadesController;
    private final Rotas rotas;

    public ClientesView(UsuariosClientesController clientesController, SistemasController sitemasController,
            EntidadesController entidadesController, AreasController areasController, Rotas rotas) {
        this.clientesController = clientesController;
        this.sistemasController = sitemasController;
        this.areasController = areasController;
        this.entidadesController = entidadesController;
        this.rotas = rotas;
    }

    @GetMapping("cliente")
    public ModelAndView pageCliente() {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/atendimento/pageCliente");
        adicionarEntidades(mv);
        adicionarAreas(mv);
        adicionarSistemas(mv);
        mv.addObject("exibir", new String[]{"Todos", "Ativos", "Inativos"});
        return mv;
    }

    private ModelAndView adicionarEntidades(ModelAndView mv) {
        List<EntidadesModel> listaEntidades = new ArrayList<>();
        EntidadesModel todasEntidades = new EntidadesModel();
        todasEntidades.setIEntidade(0);
        todasEntidades.setNomeEntidade("Todas");
        listaEntidades.add(todasEntidades);
        List<EntidadesModel> listaBanco = (List<EntidadesModel>) entidadesController.listar().getBody();
        listaBanco.forEach(entidade -> {
            listaEntidades.add(entidade);
        });
        mv.addObject("listaEntidades", listaEntidades);
        return mv;
    }

    private ModelAndView adicionarAreas(ModelAndView mv) {
        List<AreasModel> listaAreas = new ArrayList<>();
        AreasModel todasAreas = new AreasModel();
        todasAreas.setIArea(0);
        todasAreas.setDescricaoArea("Todas");
        listaAreas.add(todasAreas);
        List<AreasModel> listaBanco = (List<AreasModel>) areasController.listar().getBody();
        listaBanco.forEach(area -> {
            listaAreas.add(area);
        });
        mv.addObject("listaAreas", listaAreas);
        return mv;
    }

    private ModelAndView adicionarSistemas(ModelAndView mv) {
        List<SistemasModel> listaSistemas = new ArrayList<>();
        SistemasModel todosSistemas = new SistemasModel();
        todosSistemas.setISistema(0);
        todosSistemas.setDescricaoSistema("Todos");
        listaSistemas.add(todosSistemas);
        List<SistemasModel> listaBanco = (List<SistemasModel>) sistemasController.listar().getBody();
        listaBanco.forEach(sistema -> {
            listaSistemas.add(sistema);
        });
        mv.addObject("listaSistemas", listaSistemas);
        return mv;
    }

    @GetMapping("conteudoModalClientes")
    public ModelAndView conteudoModalEdicaoClientes(@Valid @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "abertura", required = false) boolean abertura) {
        rotas.viewsDaRota();
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            if (abertura) {
                mv.setViewName(rotas.CADASTRAR_CLIENTE_ABERTURA);
            } else {
                mv.setViewName(rotas.CADASTRAR_CLIENTE);
            }
            mv.addObject("clienteModel", new UsuariosClientesModel());
        } else {
            mv.setViewName(rotas.EDITAR_CLIENTE);
            mv.addObject("clienteModel", (UsuariosClientesModel) clientesController.usuarioCliente(id).getBody());
        }
        mv.addObject("listaSistemas", (List<SistemasModel>) sistemasController.listar().getBody());
        mv.addObject("listaAreas", (List<AreasModel>) areasController.listar().getBody());
        mv.addObject("listaEntidades", (List<EntidadesModel>) entidadesController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

}
