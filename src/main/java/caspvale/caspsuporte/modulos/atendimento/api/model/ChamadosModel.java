package caspvale.caspsuporte.modulos.atendimento.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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
public class ChamadosModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iChamado;
    private String resumoChamado;
    private String descricaoChamado;
    private String descricaoProblema;
    private String descricaoSolucao;
    private String contatoSolicitante;
    private String emailSolicitante;
    private UsuariosInputModel iUsuarioAbertura;
    private UsuariosInputModel iUsuarioAtendimento;
    private UsuariosInputModel iUsuarioEncerramento;
    private UsuariosInputModel iUsuarioEncaminhamento;
    private List<AreasInputModel> caspAreasList;
    private List<EntidadesInputModel> caspEntidadesList;
    private List<SistemasInputModel> caspSistemasList;
    private List<ProblemasInputModel> caspProblemasList;
    private List<UsuariosInputModel> caspUsuariosList;
    private List<AnexosInputModel> caspAnexosList;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAtendimento;
    private LocalDateTime dtAlteracao;
    private PrioridadesInputModel iPrioridade;
    private NiveisInputModel iNivel;
    private OrigensInputModel iOrigemChamado;
    private SituacoesModel iSituacao;
    private LocalTime tempoAtendimento;
    private LocalTime tempoSolucao;
    private LocalTime tempoTotal;
    private String casoBetha;
    private String comentarioChamado;
    private String hostoricoChat;
    private String historicoEmail;
    private String situacaoChamado;
    private String usuarioAlteracao;
    
    public ChamadosModel(UsuariosInputModel iUsuarioAbertura){
        this.iUsuarioAbertura = iUsuarioAbertura;
    }
    
    @Override
    public String toString(){
        return iChamado.toString();
    }
}
