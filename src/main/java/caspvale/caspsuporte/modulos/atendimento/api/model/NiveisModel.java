/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;


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
public class NiveisModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iNivel;
    private String descricaoNivel;
    private String situacaoNivel;
    //private List<CaspUsuariosConfig> caspUsuariosConfigList;
    
    @Override
    public String toString(){
        return iNivel.toString();
    }
}
