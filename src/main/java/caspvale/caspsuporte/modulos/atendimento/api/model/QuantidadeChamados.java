/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;

/**
 *
 * @author Hilton
 */
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class QuantidadeChamados {
    private int totalAguardando;
    private int totalEmAtendimento;
    private int totalEncerrado;
}
