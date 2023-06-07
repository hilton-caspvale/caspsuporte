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
@Table(name = "casp_niveis",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspNiveis.findAll", query = "SELECT c FROM CaspNiveis c"),
    @NamedQuery(name = "CaspNiveis.findByINivel", query = "SELECT c FROM CaspNiveis c WHERE c.iNivel = :iNivel"),
    @NamedQuery(name = "CaspNiveis.findByDescricaoNivel", query = "SELECT c FROM CaspNiveis c WHERE c.descricaoNivel = :descricaoNivel"),
    @NamedQuery(name = "CaspNiveis.findBySituacaoNivel", query = "SELECT c FROM CaspNiveis c WHERE c.situacaoNivel = :situacaoNivel")})
public class CaspNiveis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_nivel")
    private Integer iNivel;
    @Size(max = 2147483647)
    @Column(name = "descricao_nivel")
    private String descricaoNivel;
    @Size(max = 2147483647)
    @Column(name = "situacao_nivel")
    private String situacaoNivel;
    @OneToMany(mappedBy = "iNivel")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iNivel")
    private List<CaspChamados> caspChamadosList;

    public CaspNiveis() {
    }

    public CaspNiveis(Integer iNivel) {
        this.iNivel = iNivel;
    }

    public Integer getINivel() {
        return iNivel;
    }

    public void setINivel(Integer iNivel) {
        this.iNivel = iNivel;
    }

    public String getDescricaoNivel() {
        return descricaoNivel;
    }

    public void setDescricaoNivel(String descricaoNivel) {
        this.descricaoNivel = descricaoNivel;
    }

    public String getSituacaoNivel() {
        return situacaoNivel;
    }

    public void setSituacaoNivel(String situacaoNivel) {
        this.situacaoNivel = situacaoNivel;
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
        hash += (iNivel != null ? iNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspNiveis)) {
            return false;
        }
        CaspNiveis other = (CaspNiveis) object;
        if ((this.iNivel == null && other.iNivel != null) || (this.iNivel != null && !this.iNivel.equals(other.iNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspNiveis[ iNivel=" + iNivel + " ]";
    }
    
}
