/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.model;

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
@Table(name = "casp_origens",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspOrigens.findAll", query = "SELECT c FROM CaspOrigens c"),
    @NamedQuery(name = "CaspOrigens.findByIOrigemChamado", query = "SELECT c FROM CaspOrigens c WHERE c.iOrigemChamado = :iOrigemChamado"),
    @NamedQuery(name = "CaspOrigens.findByDescricaoOrigem", query = "SELECT c FROM CaspOrigens c WHERE c.descricaoOrigem = :descricaoOrigem"),
    @NamedQuery(name = "CaspOrigens.findBySituacaoOrigem", query = "SELECT c FROM CaspOrigens c WHERE c.situacaoOrigem = :situacaoOrigem")})
public class CaspOrigens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_origem_chamado")
    private Integer iOrigemChamado;
    @Size(max = 2147483647)
    @Column(name = "descricao_origem")
    private String descricaoOrigem;
    @Size(max = 2147483647)
    @Column(name = "situacao_origem")
    private String situacaoOrigem;
    @OneToMany(mappedBy = "iOrigemChamado")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iOrigemChamado")
    private List<CaspChamados> caspChamadosList;

    public CaspOrigens() {
    }

    public CaspOrigens(Integer iOrigemChamado) {
        this.iOrigemChamado = iOrigemChamado;
    }

    public Integer getIOrigemChamado() {
        return iOrigemChamado;
    }

    public void setIOrigemChamado(Integer iOrigemChamado) {
        this.iOrigemChamado = iOrigemChamado;
    }

    public String getDescricaoOrigem() {
        return descricaoOrigem;
    }

    public void setDescricaoOrigem(String descricaoOrigem) {
        this.descricaoOrigem = descricaoOrigem;
    }

    public String getSituacaoOrigem() {
        return situacaoOrigem;
    }

    public void setSituacaoOrigem(String situacaoOrigem) {
        this.situacaoOrigem = situacaoOrigem;
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
        hash += (iOrigemChamado != null ? iOrigemChamado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspOrigens)) {
            return false;
        }
        CaspOrigens other = (CaspOrigens) object;
        if ((this.iOrigemChamado == null && other.iOrigemChamado != null) || (this.iOrigemChamado != null && !this.iOrigemChamado.equals(other.iOrigemChamado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspOrigens[ iOrigemChamado=" + iOrigemChamado + " ]";
    }
    
}
