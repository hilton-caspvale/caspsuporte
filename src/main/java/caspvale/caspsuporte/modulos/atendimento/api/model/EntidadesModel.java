package caspvale.caspsuporte.modulos.atendimento.api.model;

import java.io.Serializable;
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
public class EntidadesModel implements Serializable {
private static final long serialVersionUID = 1L;
    private Integer iEntidade;
    private String nomeEntidade;
    private String cnpjEntidade;
    private String telefoneEntidade;
    private String emailEntidade;
    private String enderecoEntidade;
    private String dadosServidor;
    private String comentarioEntidade;
    private String situacaoEntidade;
    private TiposEntidadesModel iTipoEntidade;
    
@Override
    public String toString(){
        return iEntidade.toString();
    }
}
