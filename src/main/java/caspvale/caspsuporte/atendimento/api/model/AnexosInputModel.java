package caspvale.caspsuporte.atendimento.api.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Hilton
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AnexosInputModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iAnexo;
    private String comentarioArquivo;
    private String descricaoArquivo;
    private Integer iArquivo;    
    private UsuariosInputModel iUsuario; 
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataArquivo;
    private TiposArquivosInputModel iTiposArquivos;
    
    @Override
    public String toString(){
        return iAnexo.toString();
    }
}
