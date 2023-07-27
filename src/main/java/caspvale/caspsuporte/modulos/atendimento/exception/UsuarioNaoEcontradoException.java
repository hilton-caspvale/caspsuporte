/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.exception;

/**
 *
 * @author Hilton
 */
public class UsuarioNaoEcontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of <code>EntidadeNaoEncontradaException</code>
     * with the specified detail message.
     *
     * @param mensagem the detail message.
     */
    public UsuarioNaoEcontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEcontradoException(Integer id) {
        this(String.format("Não existe um usuário com o código %d", id));
    }
}
