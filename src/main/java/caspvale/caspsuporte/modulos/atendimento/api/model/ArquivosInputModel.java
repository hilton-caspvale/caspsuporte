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
public class ArquivosInputModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iArquivo;
    
    @Override
    public String toString(){
        return iArquivo.toString();
    }
}
