package caspvale.caspsuporte.atendimento.view;

import caspvale.caspsuporte.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.atendimento.api.controller.ChamadosController;
import caspvale.caspsuporte.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.atendimento.api.controller.ProblemasController;
import caspvale.caspsuporte.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.atendimento.api.controller.UsuariosController;
import caspvale.caspsuporte.atendimento.api.model.ChamadosModel;
import caspvale.caspsuporte.atendimento.domain.service.ChamadosService;
import caspvale.caspsuporte.atendimento.common.Rotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/mv/atendimento")
public class AtendimentoView {

    @Autowired
    private Rotas rotas;
   

    @GetMapping
    public ModelAndView atendimento() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/atendimento");
    }

    @GetMapping("menuAtendimento")
    public ModelAndView menuAtendimento() {
        rotas.viewsDaRota();
        return new ModelAndView(rotas.MENU_ATENDIMENTO);
    }

    @GetMapping("toast")
    public ModelAndView toast() {
        return new ModelAndView("/atendimento/components/toast");
    }    

}