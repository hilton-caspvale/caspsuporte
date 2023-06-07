/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Hilton
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada!")
public /*abstract*/ class EntidadeNaoEncontradaException extends NegocioException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of <code>EntidadeNaoEncontradaException</code>
     * with the specified detail message.
     *
     * @param mensagem the detail message.
     */
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
