/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "remoto_tipos_acessos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemotoTiposAcessos.findAll", query = "SELECT r FROM RemotoTiposAcessos r"),
    @NamedQuery(name = "RemotoTiposAcessos.findByITipo", query = "SELECT r FROM RemotoTiposAcessos r WHERE r.iTipo = :iTipo"),
    @NamedQuery(name = "RemotoTiposAcessos.findByDescricaoTipo", query = "SELECT r FROM RemotoTiposAcessos r WHERE r.descricaoTipo = :descricaoTipo")})
public class RemotoTiposAcessos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_tipo")
    private Integer iTipo;
    @Size(max = 2147483647)
    @Column(name = "descricao_tipo")
    private String descricaoTipo;

    public RemotoTiposAcessos() {
    }

    public RemotoTiposAcessos(Integer iTipo) {
        this.iTipo = iTipo;
    }

    public Integer getITipo() {
        return iTipo;
    }

    public void setITipo(Integer iTipo) {
        this.iTipo = iTipo;
    }

    public String getDescricaoTipo() {
        return descricaoTipo;
    }

    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTipo != null ? iTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemotoTiposAcessos)) {
            return false;
        }
        RemotoTiposAcessos other = (RemotoTiposAcessos) object;
        if ((this.iTipo == null && other.iTipo != null) || (this.iTipo != null && !this.iTipo.equals(other.iTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.RemotoTiposAcessos[ iTipo=" + iTipo + " ]";
    }
    
}
