/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_usuarios_config",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspUsuariosConfig.findAll", query = "SELECT c FROM CaspUsuariosConfig c"),
    @NamedQuery(name = "CaspUsuariosConfig.findByIUsuarioConfig", query = "SELECT c FROM CaspUsuariosConfig c WHERE c.iUsuarioConfig = :iUsuarioConfig"),
    @NamedQuery(name = "CaspUsuariosConfig.findBySituacaoUsuconfig", query = "SELECT c FROM CaspUsuariosConfig c WHERE c.situacaoUsuconfig = :situacaoUsuconfig")})
public class CaspUsuariosConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_usuario_config")
    private Integer iUsuarioConfig;
    @Size(max = 2147483647)
    @Column(name = "situacao_usuconfig")
    private String situacaoUsuconfig;
    @JoinColumn(name = "i_area", referencedColumnName = "i_area")
    @ManyToOne
    private CaspAreas iArea;
    @JoinColumn(name = "i_entidade", referencedColumnName = "i_entidade")
    @ManyToOne
    private CaspEntidades iEntidade;
    @JoinColumn(name = "i_nivel", referencedColumnName = "i_nivel")
    @ManyToOne
    private CaspNiveis iNivel;
    @JoinColumn(name = "i_origem_chamado", referencedColumnName = "i_origem_chamado")
    @ManyToOne
    private CaspOrigens iOrigemChamado;
    @JoinColumn(name = "i_prioridade", referencedColumnName = "i_prioridade")
    @ManyToOne
    private CaspPrioridades iPrioridade;
    @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")
    @ManyToOne
    private CaspSistemas iSistema;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuario;

    public CaspUsuariosConfig() {
    }

    public CaspUsuariosConfig(Integer iUsuarioConfig) {
        this.iUsuarioConfig = iUsuarioConfig;
    }

    public Integer getIUsuarioConfig() {
        return iUsuarioConfig;
    }

    public void setIUsuarioConfig(Integer iUsuarioConfig) {
        this.iUsuarioConfig = iUsuarioConfig;
    }

    public String getSituacaoUsuconfig() {
        return situacaoUsuconfig;
    }

    public void setSituacaoUsuconfig(String situacaoUsuconfig) {
        this.situacaoUsuconfig = situacaoUsuconfig;
    }

    public CaspAreas getIArea() {
        return iArea;
    }

    public void setIArea(CaspAreas iArea) {
        this.iArea = iArea;
    }

    public CaspEntidades getIEntidade() {
        return iEntidade;
    }

    public void setIEntidade(CaspEntidades iEntidade) {
        this.iEntidade = iEntidade;
    }

    public CaspNiveis getINivel() {
        return iNivel;
    }

    public void setINivel(CaspNiveis iNivel) {
        this.iNivel = iNivel;
    }

    public CaspOrigens getIOrigemChamado() {
        return iOrigemChamado;
    }

    public void setIOrigemChamado(CaspOrigens iOrigemChamado) {
        this.iOrigemChamado = iOrigemChamado;
    }

    public CaspPrioridades getIPrioridade() {
        return iPrioridade;
    }

    public void setIPrioridade(CaspPrioridades iPrioridade) {
        this.iPrioridade = iPrioridade;
    }

    public CaspSistemas getISistema() {
        return iSistema;
    }

    public void setISistema(CaspSistemas iSistema) {
        this.iSistema = iSistema;
    }

    public CaspUsuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(CaspUsuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iUsuarioConfig != null ? iUsuarioConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspUsuariosConfig)) {
            return false;
        }
        CaspUsuariosConfig other = (CaspUsuariosConfig) object;
        if ((this.iUsuarioConfig == null && other.iUsuarioConfig != null) || (this.iUsuarioConfig != null && !this.iUsuarioConfig.equals(other.iUsuarioConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspUsuariosConfig[ iUsuarioConfig=" + iUsuarioConfig + " ]";
    }
    
}
