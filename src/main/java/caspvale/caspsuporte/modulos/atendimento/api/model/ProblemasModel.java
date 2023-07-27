/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class ProblemasModel {
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iProblema;
    @NotEmpty
    private String descricaoProblema;
    private String situacaoProblema;
    private List<SistemasInputModel> caspSistemasList;
    
    @Override
    public String toString() {
        return  iProblema.toString();
    }
}
