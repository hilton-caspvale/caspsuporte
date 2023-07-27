package caspvale.caspsuporte.modulos.atendimento.view;

import caspvale.caspsuporte.modulos.atendimento.api.controller.TiposArquivosController;
import caspvale.caspsuporte.modulos.atendimento.api.model.TiposArquivosModel;
import caspvale.caspsuporte.modulos.atendimento.common.Rotas;
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
public class TiposArquivosView {
    
    private final TiposArquivosController tiposArquivosController;
    private final Rotas rotas;

    public TiposArquivosView(TiposArquivosController tiposArquivosController, Rotas rotas) {
        this.tiposArquivosController = tiposArquivosController;
        this.rotas = rotas;
    }
    
    @GetMapping("tipoArquivo")
    public ModelAndView pageTipoArquivo() {
        rotas.viewsDaRota();
        return new ModelAndView("/atendimento/pageTipoArquivo");
    }
    
    @GetMapping("conteudoModalTiposArquivos")
    public ModelAndView conteudoModalTiposArquivos(@Valid @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName(rotas.CADASTRAR_TIPO_ARQUIVO);
            mv.addObject("tipoArquivoModel", new TiposArquivosModel());
        } else {
            mv.setViewName(rotas.EDITAR_TIPO_ARQUIVO);
            mv.addObject("tipoArquivoModel", tiposArquivosController.tipoArquivo(id).getBody());
        }
        mv.addObject("situacoes", new String[]{"A", "I"});
        return mv;
    }
}
