package caspvale.caspsuporte.modulos.atendimento.api.model;

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
public class AreasModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iArea;
    @NotEmpty
    private String descricaoArea;
    private String situacaoArea;
    private String emailArea;
    private List<SistemasInputModel> caspSistemasList;
    
    @Override
    public String toString(){
        return iArea.toString();
    }
}
