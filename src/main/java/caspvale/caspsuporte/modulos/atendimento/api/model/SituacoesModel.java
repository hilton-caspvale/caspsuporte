package caspvale.caspsuporte.modulos.atendimento.api.model;


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
public class SituacoesModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iSituacao;
    @NotEmpty
    private String descricaoSituacao;
    @NotEmpty
    private String situacao;
    
    @Override
    public String toString(){
        return iSituacao.toString();
    }            
}
