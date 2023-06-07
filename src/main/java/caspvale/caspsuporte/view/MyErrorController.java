/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package caspvale.caspsuporte.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hilton
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {   
        ModelAndView mv = new ModelAndView("error");
        Integer statusCode = 0;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object statusMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object uri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        String mensagem = "Página não encontrada ou não disponível!";
        if (status != null && (status != null || statusMessage != null)) {
            statusCode = Integer.valueOf(status.toString());
            statusMessage = statusCode + " - " + statusMessage;

            switch (statusCode) {
                case 400:
                    mensagem = "O conteúdo da requisição está com sintaxe inválida!";
                    break;
                case 401:
                    mensagem = "É obrigatório a autenticação para acessar!";
                    break;
                case 403:
                    mensagem = "O acesso atual não tem permissão para acessar esse conteúdo!";
                    break;
                case 404:
                    statusMessage = "404 - Não encontrado";
                    mensagem = "Esta página não foi localizada!";
                    break;
                case 405:
                    mensagem = "O método da requisição não pode ser utilizado!";
                    break;
                case 406:
                    mensagem = "Nenhum resultado localizado com os parâmetros utilizados!";
                    break;
                case 408:
                    mensagem = "Limite de tempo excedido para a requisição!";
                    break;
                case 414:
                    mensagem = "A URI informada é muito longa!";
                    break;
                case 415:
                    mensagem = "Tipo de mídia não suportado!";
                    break;
                case 429:
                    mensagem = "Multiplas requisições seguidas. Aguarde alguns instantes!";
                    break;
                case 500:
                    mensagem = "O servidor encontrou um erro inesperado!";
                    break;
                case 503:
                    mensagem = "O servidor está indisponível no momento. Aguarde alguns instantes!";
                    break;
                default:
                    mensagem = "Página não encontrada ou não disponível!";
                    break;
            }
        }

        mv.addObject("statusCode", statusCode);
        mv.addObject("statusMessage", statusMessage);
        mv.addObject("uri", uri);
        mv.addObject("mensagem", mensagem);
        return mv;
    }

}
