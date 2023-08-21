/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "casp_situacoes",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspSituacoes.findAll", query = "SELECT c FROM CaspSituacoes c"),
    @NamedQuery(name = "CaspSituacoes.findByISituacao", query = "SELECT c FROM CaspSituacoes c WHERE c.iSituacao = :iSituacao"),
    @NamedQuery(name = "CaspSituacoes.findByDescricaoSituacao", query = "SELECT c FROM CaspSituacoes c WHERE c.descricaoSituacao = :descricaoSituacao"),
    @NamedQuery(name = "CaspSituacoes.findBySituacao", query = "SELECT c FROM CaspSituacoes c WHERE c.situacao = :situacao")})
public class CaspSituacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_situacao")
    private Integer iSituacao;
    @Size(max = 2147483647)
    @Column(name = "descricao_situacao")
    private String descricaoSituacao;
    @Size(max = 1)
    @Column(name = "situacao")
    private String situacao;
    @OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "iSituacao")
    private List<CaspChamados> caspChamadosList;

    public CaspSituacoes() {
    }

    public CaspSituacoes(Integer iSituacao) {
        this.iSituacao = iSituacao;
    }

    public Integer getISituacao() {
        return iSituacao;
    }

    public void setISituacao(Integer iSituacao) {
        this.iSituacao = iSituacao;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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
        hash += (iSituacao != null ? iSituacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspSituacoes)) {
            return false;
        }
        CaspSituacoes other = (CaspSituacoes) object;
        if ((this.iSituacao == null && other.iSituacao != null) || (this.iSituacao != null && !this.iSituacao.equals(other.iSituacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspSituacoes[ iSituacao=" + iSituacao + " ]";
    }
    
}
