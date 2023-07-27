package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.OrigensController;
import caspvale.caspsuporte.modulos.atendimento.api.model.OrigensModel;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class OrigemView {
    
    private final OrigensController origensController;    
    private final Rotas rotas;
    
    public OrigemView(OrigensController origensController, Rotas rotas) {
        this.origensController = origensController;
        this.rotas = rotas;
    }
    
    public String usuarioLogadoRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }    
    
    @GetMapping("origem")
    public ModelAndView pageOrigem() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageOrigem");
    }
    
    @GetMapping("conteudoModalOrigens")
    public ModelAndView conteudoModalOrigens(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_ORIGEM);
            mv.addObject("origemModel", new OrigensModel());
        } else {
            mv.setViewName(rotas.EDITAR_ORIGEM);
            mv.addObject("origemModel", origensController.origem(id).getBody());
        }
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
}
