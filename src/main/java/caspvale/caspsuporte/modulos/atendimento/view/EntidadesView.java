package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.TiposEntidadesController;
import caspvale.caspsuporte.modulos.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.TiposEntidadesModel;
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
public class EntidadesView {
    
    private final EntidadesController entidadesController;
    private final TiposEntidadesController tiposEntidadesController;
    private final Rotas rotas;

    public EntidadesView(EntidadesController entidadesController, TiposEntidadesController tiposEntidadesController, Rotas rotas) {
        this.entidadesController = entidadesController;
        this.tiposEntidadesController = tiposEntidadesController;
        this.rotas = rotas;
    }
    
    @GetMapping("entidade")
    public ModelAndView pageTipoEntidade() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageEntidade");
    }
    
    @GetMapping("conteudoModalEntidades")
    public ModelAndView conteudoModalEntidades(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_ENTIDADE);
            mv.addObject("entidadeModel", new EntidadesModel());
        } else {
            mv.setViewName(rotas.EDITAR_ENTIDADE);
            mv.addObject("entidadeModel", entidadesController.entidade(id).getBody());
        }
        mv.addObject("listaTiposEntidades", (List<TiposEntidadesModel>) tiposEntidadesController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

}
