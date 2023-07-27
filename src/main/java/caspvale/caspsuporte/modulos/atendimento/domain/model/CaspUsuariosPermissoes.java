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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_usuarios_permissoes",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspUsuariosPermissoes.findAll", query = "SELECT c FROM CaspUsuariosPermissoes c"),
    @NamedQuery(name = "CaspUsuariosPermissoes.findByIPermissao", query = "SELECT c FROM CaspUsuariosPermissoes c WHERE c.iPermissao = :iPermissao"),
    @NamedQuery(name = "CaspUsuariosPermissoes.findByModuloPonto", query = "SELECT c FROM CaspUsuariosPermissoes c WHERE c.moduloPonto = :moduloPonto"),
    @NamedQuery(name = "CaspUsuariosPermissoes.findByModuloAtendimento", query = "SELECT c FROM CaspUsuariosPermissoes c WHERE c.moduloAtendimento = :moduloAtendimento"),
    @NamedQuery(name = "CaspUsuariosPermissoes.findByModuloRemoto", query = "SELECT c FROM CaspUsuariosPermissoes c WHERE c.moduloRemoto = :moduloRemoto")})
public class CaspUsuariosPermissoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_permissao")
    private Integer iPermissao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modulo_ponto")
    private boolean moduloPonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modulo_atendimento")
    private boolean moduloAtendimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modulo_remoto")
    private boolean moduloRemoto;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private CaspUsuarios iUsuario;

    public CaspUsuariosPermissoes() {
    }

    public CaspUsuariosPermissoes(Integer iPermissao) {
        this.iPermissao = iPermissao;
    }

    public CaspUsuariosPermissoes(Integer iPermissao, boolean moduloPonto, boolean moduloAtendimento, boolean moduloRemoto) {
        this.iPermissao = iPermissao;
        this.moduloPonto = moduloPonto;
        this.moduloAtendimento = moduloAtendimento;
        this.moduloRemoto = moduloRemoto;
    }

    public Integer getIPermissao() {
        return iPermissao;
    }

    public void setIPermissao(Integer iPermissao) {
        this.iPermissao = iPermissao;
    }

    public boolean getModuloPonto() {
        return moduloPonto;
    }

    public void setModuloPonto(boolean moduloPonto) {
        this.moduloPonto = moduloPonto;
    }

    public boolean getModuloAtendimento() {
        return moduloAtendimento;
    }

    public void setModuloAtendimento(boolean moduloAtendimento) {
        this.moduloAtendimento = moduloAtendimento;
    }

    public boolean getModuloRemoto() {
        return moduloRemoto;
    }

    public void setModuloRemoto(boolean moduloRemoto) {
        this.moduloRemoto = moduloRemoto;
    }

    public CaspUsuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(CaspUsuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iPermissao != null ? iPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspUsuariosPermissoes)) {
            return false;
        }
        CaspUsuariosPermissoes other = (CaspUsuariosPermissoes) object;
        if ((this.iPermissao == null && other.iPermissao != null) || (this.iPermissao != null && !this.iPermissao.equals(other.iPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspUsuariosPermissoes[ iPermissao=" + iPermissao + " ]";
    }
    
}
