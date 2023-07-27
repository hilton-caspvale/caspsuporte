package caspvale.caspsuporte.modulos.atendimento.api.model;

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
public class ChamadosExibicaoModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iChamado;
    private String descricaoChamado;
    private String descricaoProblema;
    private String descricaoSolucao;
    private String contatoUsuarioAbertura;
    private String emailUsuarioAbertura;
    private String nomeUsuarioAbertura;
    private String nomeUsuarioAtendimento;
    private String nomeUsuarioEncerramento;
    private String nomeUsuarioEncaminhamento;
    private List<AreasInputModel> areas;
    private List<EntidadesInputModel> entidades;
    private List<SistemasInputModel> sistemas;
    private List<ProblemasInputModel> problemas;
    private String resumoChamado;
    private String abertura;
    private String encerramento;
    private String descricaoPrioridade;
    private String descricaoNivel;
    private String descricaoOrigem;
    private String situacaoChamado;
}
