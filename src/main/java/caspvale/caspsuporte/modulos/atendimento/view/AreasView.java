package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.modulos.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.modulos.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.modulos.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AreasView {
    
    @Autowired
    private Rotas rotas;

    private final AreasController areasController;  
    private final SistemasController sistemasController;

    public AreasView(AreasController areasController, SistemasController sistemasController) {
        this.areasController = areasController;     
        this.sistemasController = sistemasController;
    }

    @GetMapping("area")
    public ModelAndView pageArea() {        
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageArea");
    }

    @GetMapping("conteudoModalAreas")
    public ModelAndView conteudoModalEdicaoAreas(@Valid @RequestParam(value = "id", required = false) Integer iArea) {              
        ModelAndView mv = new ModelAndView();
        if (iArea == null) {
            mv.setViewName(rotas.CADASTRAR_AREA);
            mv.addObject("areaModel", new AreasModel());
        } else {
            mv.setViewName(rotas.EDITAR_AREA);
            mv.addObject("areaModel", areasController.area(iArea).getBody());
        }
        mv.addObject("listaSistemas", (List<SistemasModel>) sistemasController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }

}
