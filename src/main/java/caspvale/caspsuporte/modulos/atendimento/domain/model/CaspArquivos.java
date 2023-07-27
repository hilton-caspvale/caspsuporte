/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.atendimento.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_arquivos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspArquivos.findAll", query = "SELECT c FROM CaspArquivos c"),
    @NamedQuery(name = "CaspArquivos.findByIArquivo", query = "SELECT c FROM CaspArquivos c WHERE c.iArquivo = :iArquivo")})
public class CaspArquivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_arquivo")
    private Integer iArquivo;
    @Lob
    @Column(name = "arquivo")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] arquivo;

    public CaspArquivos() {
    }

    public CaspArquivos(Integer iArquivo) {
        this.iArquivo = iArquivo;
    }

    public Integer getIArquivo() {
        return iArquivo;
    }

    public void setIArquivo(Integer iArquivo) {
        this.iArquivo = iArquivo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iArquivo != null ? iArquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspArquivos)) {
            return false;
        }
        CaspArquivos other = (CaspArquivos) object;
        if ((this.iArquivo == null && other.iArquivo != null) || (this.iArquivo != null && !this.iArquivo.equals(other.iArquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspArquivos[ iArquivo=" + iArquivo + " ]";
    }
    
}
