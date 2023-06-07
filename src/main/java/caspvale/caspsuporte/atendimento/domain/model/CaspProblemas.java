/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.model;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_problemas",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspProblemas.findAll", query = "SELECT c FROM CaspProblemas c"),
    @NamedQuery(name = "CaspProblemas.findByIProblema", query = "SELECT c FROM CaspProblemas c WHERE c.iProblema = :iProblema"),
    @NamedQuery(name = "CaspProblemas.findByDescricaoProblema", query = "SELECT c FROM CaspProblemas c WHERE c.descricaoProblema = :descricaoProblema"),
    @NamedQuery(name = "CaspProblemas.findBySituacaoProblema", query = "SELECT c FROM CaspProblemas c WHERE c.situacaoProblema = :situacaoProblema")})
public class CaspProblemas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_problema")
    private Integer iProblema;
    @Size(max = 2147483647)
    @Column(name = "descricao_problema")
    private String descricaoProblema;
    @Size(max = 2147483647)
    @Column(name = "situacao_problema")
    private String situacaoProblema;
    //@JsonIgnore
    @JoinTable(name = "casp_sistemas_problemas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_problema", referencedColumnName = "i_problema")}, inverseJoinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")})
    @ManyToMany
    private List<CaspSistemas> caspSistemasList;
    //@JsonIgnore
    @ManyToMany(mappedBy = "caspProblemasList")
    private List<CaspChamados> caspChamadosList;

    public CaspProblemas() {
    }

    public CaspProblemas(Integer iProblema) {
        this.iProblema = iProblema;
    }

    public Integer getIProblema() {
        return iProblema;
    }

    public void setIProblema(Integer iProblema) {
        this.iProblema = iProblema;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getSituacaoProblema() {
        return situacaoProblema;
    }

    public void setSituacaoProblema(String situacaoProblema) {
        this.situacaoProblema = situacaoProblema;
    }

    @XmlTransient
    public List<CaspSistemas> getCaspSistemasList() {
        return caspSistemasList;
    }

    public void setCaspSistemasList(List<CaspSistemas> caspSistemasList) {
        this.caspSistemasList = caspSistemasList;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList() {
        return caspChamadosList;
    }

    public void setCaspChamadosList(List<CaspChamados> caspChamadosList) {
        this.caspChamadosList = caspChamadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iProblema != null ? iProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspProblemas)) {
            return false;
        }
        CaspProblemas other = (CaspProblemas) object;
        if ((this.iProblema == null && other.iProblema != null) || (this.iProblema != null && !this.iProblema.equals(other.iProblema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspProblemas[ iProblema=" + iProblema + " ]";
    }
    
}
