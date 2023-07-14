package caspvale.caspsuporte.atendimento.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class ChamadosInputModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iChamado;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAtendimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAgendamento;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEncerramento;
    private LocalDate tempoAtendimento;
    private LocalDate tempoSolucao;
    private LocalDate tempoTotal;
    @Size(max = 255)
    private String casoBetha;
    @NotBlank
    @Size(min = 10, max = 255)
    private String resumoChamado;
    @NotBlank
    @Size(min = 10, max = 2147483647)
    private String descricaoChamado;
    @Size(max = 2147483647)
    private String comentarioChamado;
    @Size(max = 2147483647)
    private String descricaoProblema;
    @Size(max = 2147483647)
    private String descricaoSolucao;
    @Size(max = 255)
    private String contatoSolicitante;
    @Size(max = 255)
    private String emailSolicitante;
    @Size(max = 2147483647)
    private String hostoricoChat;
    @Size(max = 2147483647)
    private String historicoEmail;
    @Size(max = 255)
    private String situacaoChamado;
    private String usuarioAlteracao;
    private LocalDateTime dtAlteracao;
    @NotEmpty
    private List<AreasInputModel> caspAreasList;
    @NotEmpty
    private List<EntidadesInputModel> caspEntidadesList;
    @NotEmpty
    private List<SistemasInputModel> caspSistemasList;
    @NotEmpty
    private List<ProblemasInputModel> caspProblemasList; 
    private Integer iNivel;
    private Integer iOrigemChamado;
    private Integer iPrioridade;
    private SituacoesModel iSituacao;
    private UsuariosInputModel iusuarioAtendimento;
    private UsuariosInputModel iusuarioEncaminhamento;
    private UsuariosInputModel iusuarioEncerramento;
    @NotNull
    private Integer iusuarioAbertura;
    @Size(max = 50)
    private String nlogin;
    @Size(max = 255)
    private String nomeUsuario;
    //private List<UsuariosInputModel> caspUsuariosList;
    //private List<AnexosInputModel> caspAnexosList;
}
