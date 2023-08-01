
package caspvale.caspsuporte.modulos.atendimento.api.model;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class UsuariosClientesModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iUsuario;
    @NotEmpty
    private String nlogin;
    @NotEmpty
    private String nomeUsuario;
    private String contatoUsuario;
    private String emailUsuario;
    private String situacaoUsuario;
    private List<AreasInputModel> caspAreasList;
    private List<SistemasInputModel> caspSistemasList;
    private List<EntidadesInputModel> caspEntidadesList;
}
