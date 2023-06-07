package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.NiveisController;
import caspvale.caspsuporte.atendimento.api.model.NiveisModel;
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
public class NiveisView {
    
    private final NiveisController niveisController;
    private final Rotas rotas;

    public NiveisView(NiveisController niveisController, Rotas rotas) {
        this.niveisController = niveisController;
        this.rotas = rotas;
    }
    
    @GetMapping("nivel")
    public ModelAndView pageNivel() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageNivel");
    }
    
    @GetMapping("conteudoModalNiveis")
    public ModelAndView conteudoModalNiveis(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_NIVEL);
            mv.addObject("nivelModel", new NiveisModel());
        } else {
            mv.setViewName(rotas.EDITAR_NIVEL);
            mv.addObject("nivelModel", niveisController.nivel(id).getBody());
        }
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
}
