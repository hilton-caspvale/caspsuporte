/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
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
public class TiposEntidadesModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iTiposEntidade;
    private String tipoDescricao;
    private String situacaoTipoEntidade;
    
    @Override
    public String toString(){
        return iTiposEntidade.toString();
    }
}
