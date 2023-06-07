package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.ProblemasController;
import caspvale.caspsuporte.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.atendimento.api.model.ProblemasModel;
import caspvale.caspsuporte.atendimento.api.model.SistemasModel;
import caspvale.caspsuporte.atendimento.common.Rotas;
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
public class ProblemasView {
        
    private final ProblemasController problemasController;
    private final SistemasController sistemasController;
    private final Rotas rotas;

    public ProblemasView(ProblemasController problemasController, SistemasController sistemasController, Rotas rotas) {
        this.problemasController = problemasController;
        this.sistemasController = sistemasController;
        this.rotas = rotas;
    }
    
    @GetMapping("problema")
    public ModelAndView pageTipoArquivo() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageProblema");
    }
    
    @GetMapping("conteudoModalProblemas")
    public ModelAndView conteudoModalProblemas(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_PROBLEMA);
            mv.addObject("problemasModel", new ProblemasModel());
        } else {
            mv.setViewName(rotas.EDITAR_PROBLEMA);
            mv.addObject("problemasModel", problemasController.problema(id).getBody());
        }
        mv.addObject("listaSistemas", (List<SistemasModel>) sistemasController.listar().getBody());
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
}
