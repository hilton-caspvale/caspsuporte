package caspvale.caspsuporte.atendimento.api.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspArquivos;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class AnexosModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iAnexo;
    private String diretorioArquivo;
    private String descricaoArquivo;
    private String comentarioArquivo;
    private LocalDateTime dataArquivo;
    private String situacaoArquivo;
    private TiposArquivosInputModel iTipoArquivo;
    private UsuariosInputModel iUsuario;
    //private ArquivosInputModel iArquivo;
    private Integer iArquivo;

    @Override
    public String toString() {
        return iAnexo.toString();
    }
}
