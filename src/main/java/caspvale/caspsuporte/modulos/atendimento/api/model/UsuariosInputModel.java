
package caspvale.caspsuporte.modulos.atendimento.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotEmpty;
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
public class UsuariosInputModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iUsuario;
    @NotEmpty
    private String nlogin;
    @NotEmpty
    private String nome;
    private String contato;
    
    @Override
    public String toString(){
        return iUsuario.toString();
    }
}
