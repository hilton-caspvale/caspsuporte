/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TiposUsuariosInputModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iTipoUsuario;
    @NotEmpty
    private String descricaoTipoUsuario;
    
    @Override
    public String toString(){
        return iTipoUsuario.toString();
    }
}
