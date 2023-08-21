package caspvale.caspsuporte.domain.security;

/**
 *
 * @author Hilton
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor /*implements HandlerInterceptor */{

    /*@Override*/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        request.getHeaderNames().asIterator().forEachRemaining(header -> {
//            System.out.println(header + ": " + request.getHeader(header));
//        });
        // Aqui você pode implementar a lógica para diferenciar as requisições
        // Por exemplo, você pode verificar o caminho da requisição ou algum parâmetro
        // e tomar decisões com base nisso.

        // Retorne true para permitir que o fluxo continue para o controlador ou false para interrompê-lo.
//        String sessionId = request.getSession().getId();
//        System.out.println("*handler: " + handler);
        return true;
    }

    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Método vazio ou implementação para pós-processamento
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Método vazio ou implementação para processamento após a conclusão da requisição
    }*/
}
