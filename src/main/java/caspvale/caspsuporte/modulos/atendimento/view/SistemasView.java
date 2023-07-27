package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.ProblemasController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.modulos.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.ProblemasModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
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
public class SistemasView {    
    
    private final SistemasController sistemasController;
    private final ProblemasController problemasController;
    private final AreasController areasController;
    private final Rotas rotas;

    public SistemasView(SistemasController sitemasController, ProblemasController problemasController,
            AreasController areasController,Rotas rotas) {
        this.sistemasController = sitemasController;
        this.problemasController = problemasController;
        this.areasController = areasController;
        this.rotas = rotas;
    }
    
    @GetMapping("sistema")
    public ModelAndView pageSistema() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageSistema");
    }
    
    @GetMapping("conteudoModalSistemas")
    public ModelAndView conteudoModalEdicaoAreas(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_SISTEMA);
            mv.addObject("sistemaModel", new SistemasModel());
        } else {
            mv.setViewName(rotas.EDITAR_SISTEMA);
            mv.addObject("sistemaModel", (SistemasModel) sistemasController.sistema(id).getBody());
        }
        mv.addObject("listaProblemas", (List<ProblemasModel>) problemasController.listar().getBody());
        mv.addObject("listaAreas", (List<AreasModel>) areasController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

}

