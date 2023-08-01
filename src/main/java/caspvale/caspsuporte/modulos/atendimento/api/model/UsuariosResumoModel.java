/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.api.model;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspTiposUsuarios;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Hilton
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class UsuariosResumoModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iUsuario;
    @NotEmpty
    private String nlogin;
    @NotEmpty
    private String nomeUsuario;
    private String situacaoUsuario;
    private TiposUsuariosInputModel iTipoUsuario;
    
    @Override
    public String toString(){
        return nlogin;
    }
}