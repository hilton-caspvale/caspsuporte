/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;

import java.util.List;
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
public class SistemasModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iSistema;
    private String descricaoSistema;
    private String situacaoSistema;
    private List<ProblemasInputModel> caspProblemasList;
    private List<AreasInputModel> caspAreasList;
    
    @Override
    public String toString(){
        return iSistema.toString();
    }
}
