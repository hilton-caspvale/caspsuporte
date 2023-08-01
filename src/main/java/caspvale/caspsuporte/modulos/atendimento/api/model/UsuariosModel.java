package caspvale.caspsuporte.modulos.atendimento.api.model;

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
public class UsuariosModel {

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
    private TiposUsuariosInputModel iTipoUsuario;

    @Override
    public String toString() {
        return iUsuario.toString();
    }
}
