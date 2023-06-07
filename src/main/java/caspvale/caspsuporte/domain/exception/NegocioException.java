/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package caspvale.caspsuporte.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Hilton
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of <code>EntidadeNaoEncontradaException</code>
     * with the specified detail message.
     *
     * @param mensagem the detail message.
     */
    public NegocioException(String mensagem) {
        super(mensagem);
    }
    
    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
