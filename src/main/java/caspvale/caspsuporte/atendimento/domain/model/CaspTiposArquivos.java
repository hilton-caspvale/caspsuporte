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
@Table(name = "casp_tipos_arquivos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspTiposArquivos.findAll", query = "SELECT c FROM CaspTiposArquivos c"),
    @NamedQuery(name = "CaspTiposArquivos.findByITipoArquivo", query = "SELECT c FROM CaspTiposArquivos c WHERE c.iTipoArquivo = :iTipoArquivo"),
    @NamedQuery(name = "CaspTiposArquivos.findByDescricaoTipoArquivo", query = "SELECT c FROM CaspTiposArquivos c WHERE c.descricaoTipoArquivo = :descricaoTipoArquivo"),
    @NamedQuery(name = "CaspTiposArquivos.findBySituacaoTipoArquivo", query = "SELECT c FROM CaspTiposArquivos c WHERE c.situacaoTipoArquivo = :situacaoTipoArquivo")})
public class CaspTiposArquivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_tipo_arquivo")
    private Integer iTipoArquivo;
    @Size(max = 2147483647)
    @Column(name = "descricao_tipo_arquivo")
    private String descricaoTipoArquivo;
    @Size(max = 2147483647)
    @Column(name = "situacao_tipo_arquivo")
    private String situacaoTipoArquivo;
    @OneToMany(mappedBy = "iTipoArquivo")
    private List<CaspAnexos> caspAnexosList;

    public CaspTiposArquivos() {
    }

    public CaspTiposArquivos(Integer iTipoArquivo) {
        this.iTipoArquivo = iTipoArquivo;
    }

    public Integer getITipoArquivo() {
        return iTipoArquivo;
    }

    public void setITipoArquivo(Integer iTipoArquivo) {
        this.iTipoArquivo = iTipoArquivo;
    }

    public String getDescricaoTipoArquivo() {
        return descricaoTipoArquivo;
    }

    public void setDescricaoTipoArquivo(String descricaoTipoArquivo) {
        this.descricaoTipoArquivo = descricaoTipoArquivo;
    }

    public String getSituacaoTipoArquivo() {
        return situacaoTipoArquivo;
    }

    public void setSituacaoTipoArquivo(String situacaoTipoArquivo) {
        this.situacaoTipoArquivo = situacaoTipoArquivo;
    }

    @XmlTransient
    public List<CaspAnexos> getCaspAnexosList() {
        return caspAnexosList;
    }

    public void setCaspAnexosList(List<CaspAnexos> caspAnexosList) {
        this.caspAnexosList = caspAnexosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTipoArquivo != null ? iTipoArquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspTiposArquivos)) {
            return false;
        }
        CaspTiposArquivos other = (CaspTiposArquivos) object;
        if ((this.iTipoArquivo == null && other.iTipoArquivo != null) || (this.iTipoArquivo != null && !this.iTipoArquivo.equals(other.iTipoArquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspTiposArquivos[ iTipoArquivo=" + iTipoArquivo + " ]";
    }
    
}
