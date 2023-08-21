/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

import caspvale.caspsuporte.modulos.remoto.domain.model.RemotoListaEspera;
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
@Table(name = "casp_prioridades",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspPrioridades.findAll", query = "SELECT c FROM CaspPrioridades c"),
    @NamedQuery(name = "CaspPrioridades.findByIPrioridade", query = "SELECT c FROM CaspPrioridades c WHERE c.iPrioridade = :iPrioridade"),
    @NamedQuery(name = "CaspPrioridades.findByDescricaoPrioridade", query = "SELECT c FROM CaspPrioridades c WHERE c.descricaoPrioridade = :descricaoPrioridade"),
    @NamedQuery(name = "CaspPrioridades.findBySituacaoPrioridade", query = "SELECT c FROM CaspPrioridades c WHERE c.situacaoPrioridade = :situacaoPrioridade")})
public class CaspPrioridades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_prioridade")
    private Integer iPrioridade;
    @Size(max = 2147483647)
    @Column(name = "descricao_prioridade")
    private String descricaoPrioridade;
    @Size(max = 2147483647)
    @Column(name = "situacao_prioridade")
    private String situacaoPrioridade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iPrioridade")
    private List<RemotoListaEspera> remotoListaEsperaList;
    @OneToMany(mappedBy = "iPrioridade")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;
    @OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "iPrioridade")
    private List<CaspChamados> caspChamadosList;

    public CaspPrioridades() {
    }

    public CaspPrioridades(Integer iPrioridade) {
        this.iPrioridade = iPrioridade;
    }

    public Integer getIPrioridade() {
        return iPrioridade;
    }

    public void setIPrioridade(Integer iPrioridade) {
        this.iPrioridade = iPrioridade;
    }

    public String getDescricaoPrioridade() {
        return descricaoPrioridade;
    }

    public void setDescricaoPrioridade(String descricaoPrioridade) {
        this.descricaoPrioridade = descricaoPrioridade;
    }

    public String getSituacaoPrioridade() {
        return situacaoPrioridade;
    }

    public void setSituacaoPrioridade(String situacaoPrioridade) {
        this.situacaoPrioridade = situacaoPrioridade;
    }

    @XmlTransient
    public List<RemotoListaEspera> getRemotoListaEsperaList() {
        return remotoListaEsperaList;
    }

    public void setRemotoListaEsperaList(List<RemotoListaEspera> remotoListaEsperaList) {
        this.remotoListaEsperaList = remotoListaEsperaList;
    }

    @XmlTransient
    public List<CaspUsuariosConfig> getCaspUsuariosConfigList() {
        return caspUsuariosConfigList;
    }

    public void setCaspUsuariosConfigList(List<CaspUsuariosConfig> caspUsuariosConfigList) {
        this.caspUsuariosConfigList = caspUsuariosConfigList;
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
        hash += (iPrioridade != null ? iPrioridade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspPrioridades)) {
            return false;
        }
        CaspPrioridades other = (CaspPrioridades) object;
        if ((this.iPrioridade == null && other.iPrioridade != null) || (this.iPrioridade != null && !this.iPrioridade.equals(other.iPrioridade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspPrioridades[ iPrioridade=" + iPrioridade + " ]";
    }
    
}
