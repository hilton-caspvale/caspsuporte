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
public class NiveisInputModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iNivel;
    private String descricaoNivel;
    
    @Override
    public String toString(){
        return iNivel.toString();
    }
}
