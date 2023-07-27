/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

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
@Table(name = "casp_areas",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspAreas.findAll", query = "SELECT c FROM CaspAreas c"),
    @NamedQuery(name = "CaspAreas.findByIArea", query = "SELECT c FROM CaspAreas c WHERE c.iArea = :iArea"),
    @NamedQuery(name = "CaspAreas.findByDescricaoArea", query = "SELECT c FROM CaspAreas c WHERE c.descricaoArea = :descricaoArea"),
    @NamedQuery(name = "CaspAreas.findBySituacaoArea", query = "SELECT c FROM CaspAreas c WHERE c.situacaoArea = :situacaoArea"),
    @NamedQuery(name = "CaspAreas.findByEmailArea", query = "SELECT c FROM CaspAreas c WHERE c.emailArea = :emailArea")})
public class CaspAreas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_area")
    private Integer iArea;
    @Size(max = 2147483647)
    @Column(name = "descricao_area")
    private String descricaoArea;
    @Size(max = 2147483647)
    @Column(name = "situacao_area")
    private String situacaoArea;
    @Size(max = 2147483647)
    @Column(name = "email_area")
    private String emailArea;
    //@JsonIgnore
    @JoinTable(name = "casp_chamados_areas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")}, inverseJoinColumns = {
        @JoinColumn(name = "i_chamado", referencedColumnName = "i_chamado")})
    @ManyToMany
    private List<CaspChamados> caspChamadosList;
    //@JsonIgnore
    @JoinTable(name = "casp_sistemas_areas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")}, inverseJoinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")})
    @ManyToMany
    private List<CaspSistemas> caspSistemasList;
    //@JsonIgnore
    @JoinTable(name = "casp_usuarios_areas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")}, inverseJoinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")})
    @ManyToMany
    private List<CaspUsuarios> caspUsuariosList;
    //@JsonIgnore
    @OneToMany(mappedBy = "iArea")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;

    public CaspAreas() {
    }

    public CaspAreas(Integer iArea) {
        this.iArea = iArea;
    }

    public Integer getIArea() {
        return iArea;
    }

    public void setIArea(Integer iArea) {
        this.iArea = iArea;
    }

    public String getDescricaoArea() {
        return descricaoArea;
    }

    public void setDescricaoArea(String descricaoArea) {
        this.descricaoArea = descricaoArea;
    }

    public String getSituacaoArea() {
        return situacaoArea;
    }

    public void setSituacaoArea(String situacaoArea) {
        this.situacaoArea = situacaoArea;
    }

    public String getEmailArea() {
        return emailArea;
    }

    public void setEmailArea(String emailArea) {
        this.emailArea = emailArea;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList() {
        return caspChamadosList;
    }

    public void setCaspChamadosList(List<CaspChamados> caspChamadosList) {
        this.caspChamadosList = caspChamadosList;
    }

    @XmlTransient
    public List<CaspSistemas> getCaspSistemasList() {
        return caspSistemasList;
    }

    public void setCaspSistemasList(List<CaspSistemas> caspSistemasList) {
        this.caspSistemasList = caspSistemasList;
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
        hash += (iArea != null ? iArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspAreas)) {
            return false;
        }
        CaspAreas other = (CaspAreas) object;
        if ((this.iArea == null && other.iArea != null) || (this.iArea != null && !this.iArea.equals(other.iArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iArea.toString();
    }
    
}
