package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.TiposEntidadesController;
import caspvale.caspsuporte.atendimento.api.model.TiposEntidadesModel;
import caspvale.caspsuporte.atendimento.common.Rotas;
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
public class TiposEntidadesView {
    
    private final TiposEntidadesController tiposEntidadesController;
    private final Rotas rotas;

    public TiposEntidadesView(TiposEntidadesController tiposEntidadesController, Rotas rotas) {
        this.tiposEntidadesController = tiposEntidadesController;
        this.rotas = rotas;
    }
    
    @GetMapping("tipoEntidade")
    public ModelAndView pageTipoEntidade() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageTipoEntidade");
    }
    
    @GetMapping("conteudoModalTiposEntidades")
    public ModelAndView conteudoModalTiposEntidades(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_TIPO_ENTIDADE);
            mv.addObject("tipoEntidadeModel", new TiposEntidadesModel());
        } else {
            mv.setViewName(rotas.EDITAR_TIPO_ENTIDADE);
            mv.addObject("tipoEntidadeModel", tiposEntidadesController.tiposEntidade(id).getBody());
        }
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

}
