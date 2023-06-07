package caspvale.caspsuporte.atendimento.view.common;

import caspvale.caspsuporte.atendimento.api.controller.AreasController;
import caspvale.caspsuporte.atendimento.api.controller.EntidadesController;
import caspvale.caspsuporte.atendimento.api.controller.SistemasController;
import caspvale.caspsuporte.atendimento.api.model.AreasModel;
import caspvale.caspsuporte.atendimento.api.model.EntidadesModel;
import caspvale.caspsuporte.atendimento.api.model.SistemasModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hilton
 */
@Configuration
public class ViewObjects {

    public ModelAndView listaEntidades(ModelAndView mv, EntidadesController entidadesController) {
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

    public ModelAndView listaAreas(ModelAndView mv, AreasController areasController) {
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

    public ModelAndView listaSistemas(ModelAndView mv, SistemasController sistemasController) {
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
}
