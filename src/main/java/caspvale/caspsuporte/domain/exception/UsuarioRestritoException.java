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
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UsuarioRestritoException extends NegocioException{
    
    private static final long serialVersionUID = 1L;
    
    public UsuarioRestritoException(){
        super("Sem privilégios para realizar a consulta!");
    }

    /**
     * Constructs an instance of <code>UsuarioRestritoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioRestritoException(String msg) {
        super(msg);
    }
}
