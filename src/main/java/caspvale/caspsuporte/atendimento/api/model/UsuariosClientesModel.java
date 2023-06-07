/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class UsuariosClientesModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iUsuario;
    @NotBlank
    @NotNull
    private String nlogin;
//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String senha;
    @NotBlank
    @NotNull
    private String nomeUsuario;
    private String contatoUsuario;
    private String emailUsuario;
    private String situacaoUsuario;
    private List<AreasInputModel> caspAreasList;
    private List<SistemasInputModel> caspSistemasList;
    private List<EntidadesInputModel> caspEntidadesList;
//
//    @JsonIgnore
//    private Boolean usuarioFuncionario;    
//    @JsonIgnore
//    private Boolean usuarioGestor;
//    @JsonIgnore    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private TiposUsuariosModel iTipoUsuario;
//    private Boolean recebeEmailHorarios;
//    @JsonIgnore
//    private String mac;
//    private List<CaspChamados> caspChamadosList;
//    private List<PontoEncerramentos> pontoEncerramentosList;
//    private List<PontoJustificativas> pontoJustificativasList;
//    private List<RemotoListaEspera> remotoListaEsperaList;
//    private List<PontoHorarios> pontoHorariosList;
//    private List<RemotoContatos> remotoContatosList;
//    private List<CaspUsuariosConfig> caspUsuariosConfigList;

//    private List<CaspChamados> caspChamadosList1;
//    private List<CaspChamados> caspChamadosList2;
//    private List<CaspChamados> caspChamadosList3;
//    private List<CaspChamados> caspChamadosList4;
//    private List<CaspUsuariosPermissoes> caspUsuariosPermissoesList;
//    private List<CaspAnexos> caspAnexosList;
}
