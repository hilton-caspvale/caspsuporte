package caspvale.caspsuporte.modulos.atendimento.api.model;

import java.io.Serializable;
import java.util.List;
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
public class EntidadesUsuariosModel implements Serializable {
private static final long serialVersionUID = 1L;
    private Integer iEntidade;
    @NotEmpty
    private String nomeEntidade;
    private String cnpjEntidade;
    private String telefoneEntidade;
    private String emailEntidade;
    private String enderecoEntidade;
    private String dadosServidor;
    private String comentarioEntidade;
    private String situacaoEntidade;
    private String tipoDescricao;
    private List<UsuariosResumoModel> caspUsuariosList;
    
@Override
    public String toString(){
        return iEntidade.toString();
    }
}
