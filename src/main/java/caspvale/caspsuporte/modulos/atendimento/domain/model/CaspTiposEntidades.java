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
@Table(name = "casp_tipos_entidades",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspTiposEntidades.findAll", query = "SELECT c FROM CaspTiposEntidades c"),
    @NamedQuery(name = "CaspTiposEntidades.findByITiposEntidade", query = "SELECT c FROM CaspTiposEntidades c WHERE c.iTiposEntidade = :iTiposEntidade"),
    @NamedQuery(name = "CaspTiposEntidades.findBySituacaoTipoEntidade", query = "SELECT c FROM CaspTiposEntidades c WHERE c.situacaoTipoEntidade = :situacaoTipoEntidade"),
    @NamedQuery(name = "CaspTiposEntidades.findByTipoDescricao", query = "SELECT c FROM CaspTiposEntidades c WHERE c.tipoDescricao = :tipoDescricao")})
public class CaspTiposEntidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_tipos_entidade")
    private Integer iTiposEntidade;
    @Size(max = 2147483647)
    @Column(name = "situacao_tipo_entidade")
    private String situacaoTipoEntidade;
    @Size(max = 2147483647)
    @Column(name = "tipo_descricao")
    private String tipoDescricao;
    @OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "iTipoEntidade")
    private List<CaspEntidades> caspEntidadesList;

    public CaspTiposEntidades() {
    }

    public CaspTiposEntidades(Integer iTiposEntidade) {
        this.iTiposEntidade = iTiposEntidade;
    }

    public Integer getITiposEntidade() {
        return iTiposEntidade;
    }

    public void setITiposEntidade(Integer iTiposEntidade) {
        this.iTiposEntidade = iTiposEntidade;
    }

    public String getSituacaoTipoEntidade() {
        return situacaoTipoEntidade;
    }

    public void setSituacaoTipoEntidade(String situacaoTipoEntidade) {
        this.situacaoTipoEntidade = situacaoTipoEntidade;
    }

    public String getTipoDescricao() {
        return tipoDescricao;
    }

    public void setTipoDescricao(String tipoDescricao) {
        this.tipoDescricao = tipoDescricao;
    }

    @XmlTransient
    public List<CaspEntidades> getCaspEntidadesList() {
        return caspEntidadesList;
    }

    public void setCaspEntidadesList(List<CaspEntidades> caspEntidadesList) {
        this.caspEntidadesList = caspEntidadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTiposEntidade != null ? iTiposEntidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspTiposEntidades)) {
            return false;
        }
        CaspTiposEntidades other = (CaspTiposEntidades) object;
        if ((this.iTiposEntidade == null && other.iTiposEntidade != null) || (this.iTiposEntidade != null && !this.iTiposEntidade.equals(other.iTiposEntidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspTiposEntidades[ iTiposEntidade=" + iTiposEntidade + " ]";
    }
    
}
