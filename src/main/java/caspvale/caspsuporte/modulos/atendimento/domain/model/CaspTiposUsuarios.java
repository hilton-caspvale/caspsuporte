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
@Table(name = "casp_tipos_usuarios",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspTiposUsuarios.findAll", query = "SELECT c FROM CaspTiposUsuarios c"),
    @NamedQuery(name = "CaspTiposUsuarios.findByITipoUsuario", query = "SELECT c FROM CaspTiposUsuarios c WHERE c.iTipoUsuario = :iTipoUsuario"),
    @NamedQuery(name = "CaspTiposUsuarios.findByDescricaoTipoUsuario", query = "SELECT c FROM CaspTiposUsuarios c WHERE c.descricaoTipoUsuario = :descricaoTipoUsuario"),
    @NamedQuery(name = "CaspTiposUsuarios.findBySituacaoTipoUsuario", query = "SELECT c FROM CaspTiposUsuarios c WHERE c.situacaoTipoUsuario = :situacaoTipoUsuario")})
public class CaspTiposUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_tipo_usuario")
    private Integer iTipoUsuario;
    @Size(max = 2147483647)
    @Column(name = "descricao_tipo_usuario")
    private String descricaoTipoUsuario;
    @Size(max = 2147483647)
    @Column(name = "situacao_tipo_usuario")
    private String situacaoTipoUsuario;
    @OneToMany(/*cascade = CascadeType.ALL,*/ mappedBy = "iTipoUsuario")
    private List<CaspUsuarios> caspUsuariosList;

    public CaspTiposUsuarios() {
    }

    public CaspTiposUsuarios(Integer iTipoUsuario) {
        this.iTipoUsuario = iTipoUsuario;
    }

    public Integer getITipoUsuario() {
        return iTipoUsuario;
    }

    public void setITipoUsuario(Integer iTipoUsuario) {
        this.iTipoUsuario = iTipoUsuario;
    }

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }

    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }

    public String getSituacaoTipoUsuario() {
        return situacaoTipoUsuario;
    }

    public void setSituacaoTipoUsuario(String situacaoTipoUsuario) {
        this.situacaoTipoUsuario = situacaoTipoUsuario;
    }

    @XmlTransient
    public List<CaspUsuarios> getCaspUsuariosList() {
        return caspUsuariosList;
    }

    public void setCaspUsuariosList(List<CaspUsuarios> caspUsuariosList) {
        this.caspUsuariosList = caspUsuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTipoUsuario != null ? iTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspTiposUsuarios)) {
            return false;
        }
        CaspTiposUsuarios other = (CaspTiposUsuarios) object;
        if ((this.iTipoUsuario == null && other.iTipoUsuario != null) || (this.iTipoUsuario != null && !this.iTipoUsuario.equals(other.iTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspTiposUsuarios[ iTipoUsuario=" + iTipoUsuario + " ]";
    }
    
}
