/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.atendimento.domain.model;

import caspvale.caspsuporte.atendimento.domain.model.CaspAreas;
import caspvale.caspsuporte.domain.model.PontoEncerramentos;
import caspvale.caspsuporte.domain.model.PontoHorarios;
import caspvale.caspsuporte.domain.model.PontoJustificativas;
import caspvale.caspsuporte.domain.model.RemotoContatos;
import caspvale.caspsuporte.domain.model.RemotoListaEspera;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "casp_usuarios",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspUsuarios.findAll", query = "SELECT c FROM CaspUsuarios c"),
    @NamedQuery(name = "CaspUsuarios.findByIUsuario", query = "SELECT c FROM CaspUsuarios c WHERE c.iUsuario = :iUsuario"),
    @NamedQuery(name = "CaspUsuarios.findByNlogin", query = "SELECT c FROM CaspUsuarios c WHERE c.nlogin = :nlogin"),
    @NamedQuery(name = "CaspUsuarios.findBySenha", query = "SELECT c FROM CaspUsuarios c WHERE c.senha = :senha"),
    @NamedQuery(name = "CaspUsuarios.findByNomeUsuario", query = "SELECT c FROM CaspUsuarios c WHERE c.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "CaspUsuarios.findByContatoUsuario", query = "SELECT c FROM CaspUsuarios c WHERE c.contatoUsuario = :contatoUsuario"),
    @NamedQuery(name = "CaspUsuarios.findByEmailUsuario", query = "SELECT c FROM CaspUsuarios c WHERE c.emailUsuario = :emailUsuario"),
    @NamedQuery(name = "CaspUsuarios.findBySituacaoUsuario", query = "SELECT c FROM CaspUsuarios c WHERE c.situacaoUsuario = :situacaoUsuario"),
    @NamedQuery(name = "CaspUsuarios.findByUsuarioFuncionario", query = "SELECT c FROM CaspUsuarios c WHERE c.usuarioFuncionario = :usuarioFuncionario"),
    @NamedQuery(name = "CaspUsuarios.findByUsuarioGestor", query = "SELECT c FROM CaspUsuarios c WHERE c.usuarioGestor = :usuarioGestor"),
    @NamedQuery(name = "CaspUsuarios.findByRecebeEmailHorarios", query = "SELECT c FROM CaspUsuarios c WHERE c.recebeEmailHorarios = :recebeEmailHorarios"),
    @NamedQuery(name = "CaspUsuarios.findByMac", query = "SELECT c FROM CaspUsuarios c WHERE c.mac = :mac")})
public class CaspUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_usuario")
    private Integer iUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nlogin")
    private String nlogin;
    @Size(max = 2147483647)
    @Column(name = "senha")
    private String senha;
    @Size(max = 2147483647)
    @Column(name = "nome_usuario")
    private String nomeUsuario;
    @Size(max = 2147483647)
    @Column(name = "contato_usuario")
    private String contatoUsuario;
    @Size(max = 2147483647)
    @Column(name = "email_usuario")
    private String emailUsuario;
    @Size(max = 2147483647)
    @Column(name = "situacao_usuario")
    private String situacaoUsuario;
    @Column(name = "usuario_funcionario")
    private Boolean usuarioFuncionario;
    @Column(name = "usuario_gestor")
    private Boolean usuarioGestor;
    @Column(name = "recebe_email_horarios")
    private Boolean recebeEmailHorarios;
    @Size(max = 2147483647)
    @Column(name = "mac")
    private String mac;
    @JoinTable(name = "casp_usuarios_areas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "i_area", referencedColumnName = "i_area")})
    @ManyToMany
    private List<CaspAreas> caspAreasList;    
    @JoinTable(name = "casp_usuarios_sistemas",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "i_sistema", referencedColumnName = "i_sistema")})
    @ManyToMany
    private List<CaspSistemas> caspSistemasList;
    
    @JoinTable(name = "casp_usuarios_entidades",schema = "casp1", joinColumns = {
        @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "i_entidade", referencedColumnName = "i_entidade")})
    @ManyToMany
    private List<CaspEntidades> caspEntidadesList;
    @ManyToMany(mappedBy = "caspUsuariosList")
    private List<CaspChamados> caspChamadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private List<PontoEncerramentos> pontoEncerramentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private List<PontoJustificativas> pontoJustificativasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private List<RemotoListaEspera> remotoListaEsperaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private List<PontoHorarios> pontoHorariosList;
    @OneToMany(mappedBy = "iUsuario")
    private List<RemotoContatos> remotoContatosList;
    @OneToMany(mappedBy = "iUsuario")
    private List<CaspUsuariosConfig> caspUsuariosConfigList;
    @JoinColumn(name = "i_tipo_usuario", referencedColumnName = "i_tipo_usuario")
    @ManyToOne(optional = false)
    private CaspTiposUsuarios iTipoUsuario;
    @OneToMany(mappedBy = "iUsuarioAbertura")
    private List<CaspChamados> caspChamadosList1;
    @OneToMany(mappedBy = "iUsuarioAtendimento")
    private List<CaspChamados> caspChamadosList2;
    @OneToMany(mappedBy = "iUsuarioEncaminhamento")
    private List<CaspChamados> caspChamadosList3;
    @OneToMany(mappedBy = "iUsuarioEncerramento")
    private List<CaspChamados> caspChamadosList4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private List<CaspUsuariosPermissoes> caspUsuariosPermissoesList;
    @OneToMany(mappedBy = "iUsuario")
    private List<CaspAnexos> caspAnexosList;

    public CaspUsuarios() {
    }

    public CaspUsuarios(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public CaspUsuarios(Integer iUsuario, String nlogin) {
        this.iUsuario = iUsuario;
        this.nlogin = nlogin;
    }

    public Integer getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public String getNlogin() {
        return nlogin;
    }

    public void setNlogin(String nlogin) {
        this.nlogin = nlogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getContatoUsuario() {
        return contatoUsuario;
    }

    public void setContatoUsuario(String contatoUsuario) {
        this.contatoUsuario = contatoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(String situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Boolean getUsuarioFuncionario() {
        return usuarioFuncionario;
    }

    public void setUsuarioFuncionario(Boolean usuarioFuncionario) {
        this.usuarioFuncionario = usuarioFuncionario;
    }

    public Boolean getUsuarioGestor() {
        return usuarioGestor;
    }

    public void setUsuarioGestor(Boolean usuarioGestor) {
        this.usuarioGestor = usuarioGestor;
    }

    public Boolean getRecebeEmailHorarios() {
        return recebeEmailHorarios;
    }

    public void setRecebeEmailHorarios(Boolean recebeEmailHorarios) {
        this.recebeEmailHorarios = recebeEmailHorarios;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @XmlTransient
    public List<CaspAreas> getCaspAreasList() {
        return caspAreasList;
    }

    public void setCaspAreasList(List<CaspAreas> caspAreasList) {
        this.caspAreasList = caspAreasList;
    }

    @XmlTransient
    public List<CaspSistemas> getCaspSistemasList() {
        return caspSistemasList;
    }

    public void setCaspSistemasList(List<CaspSistemas> caspSistemasList) {
        this.caspSistemasList = caspSistemasList;
    }

    @XmlTransient
    public List<CaspEntidades> getCaspEntidadesList() {
        return caspEntidadesList;
    }

    public void setCaspEntidadesList(List<CaspEntidades> caspEntidadesList) {
        this.caspEntidadesList = caspEntidadesList;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList() {
        return caspChamadosList;
    }

    public void setCaspChamadosList(List<CaspChamados> caspChamadosList) {
        this.caspChamadosList = caspChamadosList;
    }

    @XmlTransient
    public List<PontoEncerramentos> getPontoEncerramentosList() {
        return pontoEncerramentosList;
    }

    public void setPontoEncerramentosList(List<PontoEncerramentos> pontoEncerramentosList) {
        this.pontoEncerramentosList = pontoEncerramentosList;
    }

    @XmlTransient
    public List<PontoJustificativas> getPontoJustificativasList() {
        return pontoJustificativasList;
    }

    public void setPontoJustificativasList(List<PontoJustificativas> pontoJustificativasList) {
        this.pontoJustificativasList = pontoJustificativasList;
    }

    @XmlTransient
    public List<RemotoListaEspera> getRemotoListaEsperaList() {
        return remotoListaEsperaList;
    }

    public void setRemotoListaEsperaList(List<RemotoListaEspera> remotoListaEsperaList) {
        this.remotoListaEsperaList = remotoListaEsperaList;
    }

    @XmlTransient
    public List<PontoHorarios> getPontoHorariosList() {
        return pontoHorariosList;
    }

    public void setPontoHorariosList(List<PontoHorarios> pontoHorariosList) {
        this.pontoHorariosList = pontoHorariosList;
    }

    @XmlTransient
    public List<RemotoContatos> getRemotoContatosList() {
        return remotoContatosList;
    }

    public void setRemotoContatosList(List<RemotoContatos> remotoContatosList) {
        this.remotoContatosList = remotoContatosList;
    }

    @XmlTransient
    public List<CaspUsuariosConfig> getCaspUsuariosConfigList() {
        return caspUsuariosConfigList;
    }

    public void setCaspUsuariosConfigList(List<CaspUsuariosConfig> caspUsuariosConfigList) {
        this.caspUsuariosConfigList = caspUsuariosConfigList;
    }

    public CaspTiposUsuarios getITipoUsuario() {
        return iTipoUsuario;
    }

    public void setITipoUsuario(CaspTiposUsuarios iTipoUsuario) {
        this.iTipoUsuario = iTipoUsuario;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList1() {
        return caspChamadosList1;
    }

    public void setCaspChamadosList1(List<CaspChamados> caspChamadosList1) {
        this.caspChamadosList1 = caspChamadosList1;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList2() {
        return caspChamadosList2;
    }

    public void setCaspChamadosList2(List<CaspChamados> caspChamadosList2) {
        this.caspChamadosList2 = caspChamadosList2;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList3() {
        return caspChamadosList3;
    }

    public void setCaspChamadosList3(List<CaspChamados> caspChamadosList3) {
        this.caspChamadosList3 = caspChamadosList3;
    }

    @XmlTransient
    public List<CaspChamados> getCaspChamadosList4() {
        return caspChamadosList4;
    }

    public void setCaspChamadosList4(List<CaspChamados> caspChamadosList4) {
        this.caspChamadosList4 = caspChamadosList4;
    }

    @XmlTransient
    public List<CaspUsuariosPermissoes> getCaspUsuariosPermissoesList() {
        return caspUsuariosPermissoesList;
    }

    public void setCaspUsuariosPermissoesList(List<CaspUsuariosPermissoes> caspUsuariosPermissoesList) {
        this.caspUsuariosPermissoesList = caspUsuariosPermissoesList;
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
        hash += (iUsuario != null ? iUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspUsuarios)) {
            return false;
        }
        CaspUsuarios other = (CaspUsuarios) object;
        if ((this.iUsuario == null && other.iUsuario != null) || (this.iUsuario != null && !this.iUsuario.equals(other.iUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspUsuarios[ iUsuario=" + iUsuario + " ]";
    }
    
}
