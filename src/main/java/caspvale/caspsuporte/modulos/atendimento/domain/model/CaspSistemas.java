/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspAreas;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_sistemas", schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspSistemas.findAll", query = "SELECT c FROM CaspSistemas c"),
    @NamedQuery(name = "CaspSistemas.findByISistema", query = "SELECT c FROM CaspSistemas c WHERE c.iSistema = :iSistema"),
    @NamedQuery(name = "CaspSistemas.findByDescricaoSistema", query = "SELECT c FROM CaspSistemas c WHERE c.descricaoSistema = :descricaoSistema"),
    @NamedQuery(name = "CaspSistemas.findBySituacaoSistema", query = "SELECT c FROM CaspSistemas c WHERE c.situacaoSistema = :situacaoSistema")})
public class CaspSistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_sistema")
    private Integer iSistema;
    @Size(max = 2147483647)
    @Column(name = "descricao_sistema")
    private String descricaoSistema;
    @Size(max = 2147483647)
    @Column(name = "situacao_sistema")
    private String situacaoSistema;
    //@JsonIgnore
//    @ManyToMany(mappedBy = "caspSistemasList")
//    private List<CaspAreas> caspAreasList;
    //@JsonIgnore
    @ManyToMany(mappedBy = "caspSistemasList")
    private List<CaspChamados> caspChamadosList;
    //@JsonIgnore

    /* @ManyToMany(mappedBy = "caspSistemasList")
    private List<CaspProblemas> caspProblemasList;*/
    @JoinTable(name = "casp_sistemas_problemas", schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")}, inverseJoinColumns = {
        @JoinColumn(name = "i_problema", referencedColumnName = "i_problema")})
    @ManyToMany
    private List<CaspProblemas> caspProblemasList;
    
    @JoinTable(name = "casp_sistemas_areas", schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")}, inverseJoinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")})
    @ManyToMany
    private List<CaspAreas> caspAreasList;

    //@JsonIgnore
    @JoinTable(name = "casp_usuarios_sistemas", schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")}, inverseJoinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")})
    @ManyToMany
    private List<CaspUsuarios> caspUsuariosList;
    //@JsonIgnore
    @OneToMany(mappedBy = "iSistema")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;

    public CaspSistemas() {
    }

    public CaspSistemas(Integer iSistema) {
        this.iSistema = iSistema;
    }

    public Integer getISistema() {
        return iSistema;
    }

    public void setISistema(Integer iSistema) {
        this.iSistema = iSistema;
    }

    public String getDescricaoSistema() {
        return descricaoSistema;
    }

    public void setDescricaoSistema(String descricaoSistema) {
        this.descricaoSistema = descricaoSistema;
    }

    public String getSituacaoSistema() {
        return situacaoSistema;
    }

    public void setSituacaoSistema(String situacaoSistema) {
        this.situacaoSistema = situacaoSistema;
    }

    @XmlTransient
    public List<CaspAreas> getCaspAreasList() {
        return caspAreasList;
    }

    public void setCaspAreasList(List<CaspAreas> caspAreasList) {
        this.caspAreasList = caspAreasList;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList() {
        return caspChamadosList;
    }

    public void setCaspChamadosList(List<CaspChamados> caspChamadosList) {
        this.caspChamadosList = caspChamadosList;
    }

    @XmlTransient
    public List<CaspProblemas> getCaspProblemasList() {
        return caspProblemasList;
    }

    public void setCaspProblemasList(List<CaspProblemas> caspProblemasList) {
        this.caspProblemasList = caspProblemasList;
    }

    @XmlTransient
    public List<CaspUsuarios> getCaspUsuariosList() {
        return caspUsuariosList;
    }

    public void setCaspUsuariosList(List<CaspUsuarios> caspUsuariosList) {
        this.caspUsuariosList = caspUsuariosList;
    }

    @XmlTransient
    public List<CaspUsuariosConfig> getCaspUsuariosConfigList() {
        return caspUsuariosConfigList;
    }

    public void setCaspUsuariosConfigList(List<CaspUsuariosConfig> caspUsuariosConfigList) {
        this.caspUsuariosConfigList = caspUsuariosConfigList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iSistema != null ? iSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspSistemas)) {
            return false;
        }
        CaspSistemas other = (CaspSistemas) object;
        if ((this.iSistema == null && other.iSistema != null) || (this.iSistema != null && !this.iSistema.equals(other.iSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspSistemas[ iSistema=" + iSistema + " ]";
    }

}
