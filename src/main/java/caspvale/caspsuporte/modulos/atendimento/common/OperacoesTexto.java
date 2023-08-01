/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.common;

/**
 *
 * @author Hilton
 */
public class OperacoesTexto {
    
    public boolean textoVazio(String texto) {
        if (texto == null) {
            return true;
        }
        if (texto.trim().isEmpty()) {
            return true;
        }
        if (texto.trim().isBlank()) {
            return true;
        }
        return false;
    }
    
}
