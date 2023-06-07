/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.api.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspNiveis;
import caspvale.caspsuporte.atendimento.domain.model.CaspOrigens;
import caspvale.caspsuporte.atendimento.domain.model.CaspPrioridades;
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
public class UsuariosConfigModel {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Integer iUsuarioConfig;
    private String situacaoUsuconfig;
    private AreasModel iArea;
    private EntidadesModel iEntidade;
    private CaspNiveis iNivel;
    private CaspOrigens iOrigemChamado;
    private CaspPrioridades iPrioridade;
    private SistemasModel iSistema;
    private UsuariosModel iUsuario;
}
