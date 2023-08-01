/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;

import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Hilton
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class PrioridadesModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iPrioridade;
    @NotEmpty
    private String descricaoPrioridade;
    private String situacaoPrioridade;
    
    @Override
    public String toString(){
        return iPrioridade.toString();
    }
}
