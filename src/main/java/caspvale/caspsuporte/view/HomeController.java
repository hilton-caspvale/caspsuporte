package caspvale.caspsuporte.view;

import caspvale.caspsuporte.modulos.atendimento.common.Permissoes;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hilton
 */
@RestController
@RequestMapping("/")
public class HomeController {       
    
    private final Permissoes permissoes;
    
    public HomeController(Permissoes permissoes){
        this.permissoes = permissoes;
    }
    
    @GetMapping("login")
    public ModelAndView login(HttpServletRequest httpServletRequest) {  
        return new ModelAndView("login");
    }
    
    @RequestMapping
    public ModelAndView index() {   
        //System.out.println("index name: "+SecurityContextHolder.getContext().getAuthentication().getName());
        return new ModelAndView("index")
                .addObject("role", permissoes.role())
                .addObject("login", permissoes.login());
    }
    
    @RequestMapping("/atendimento/novo")
    public ModelAndView novoChamado() {   
        return new ModelAndView("atendimento/novo-chamado")
                .addObject("role", permissoes.role())
                .addObject("login", permissoes.login());
    }

//    @GetMapping("admin")
//    public ModelAndView admin() {
//        return new ModelAndView("admin");
//    }
//    
//    @GetMapping("cliente")
//    public ModelAndView clienteView() {
//        return new ModelAndView("cliente");
//    }
}
