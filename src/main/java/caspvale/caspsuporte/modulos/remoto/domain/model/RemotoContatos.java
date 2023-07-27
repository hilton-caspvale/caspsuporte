/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.modulos.remoto.domain.model;

import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspEntidades;
import caspvale.caspsuporte.modulos.atendimento.domain.model.CaspUsuarios;
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
@Table(name = "remoto_contatos",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemotoContatos.findAll", query = "SELECT r FROM RemotoContatos r"),
    @NamedQuery(name = "RemotoContatos.findByIContato", query = "SELECT r FROM RemotoContatos r WHERE r.iContato = :iContato"),
    @NamedQuery(name = "RemotoContatos.findByIp", query = "SELECT r FROM RemotoContatos r WHERE r.ip = :ip"),
    @NamedQuery(name = "RemotoContatos.findByPorta", query = "SELECT r FROM RemotoContatos r WHERE r.porta = :porta"),
    @NamedQuery(name = "RemotoContatos.findByDominio", query = "SELECT r FROM RemotoContatos r WHERE r.dominio = :dominio"),
    @NamedQuery(name = "RemotoContatos.findByUsuarioWindows", query = "SELECT r FROM RemotoContatos r WHERE r.usuarioWindows = :usuarioWindows"),
    @NamedQuery(name = "RemotoContatos.findBySenhaWindows", query = "SELECT r FROM RemotoContatos r WHERE r.senhaWindows = :senhaWindows"),
    @NamedQuery(name = "RemotoContatos.findByVpn", query = "SELECT r FROM RemotoContatos r WHERE r.vpn = :vpn"),
    @NamedQuery(name = "RemotoContatos.findByIdTv", query = "SELECT r FROM RemotoContatos r WHERE r.idTv = :idTv"),
    @NamedQuery(name = "RemotoContatos.findBySenhaTv", query = "SELECT r FROM RemotoContatos r WHERE r.senhaTv = :senhaTv"),
    @NamedQuery(name = "RemotoContatos.findByComandoRpd", query = "SELECT r FROM RemotoContatos r WHERE r.comandoRpd = :comandoRpd"),
    @NamedQuery(name = "RemotoContatos.findByComandoTv", query = "SELECT r FROM RemotoContatos r WHERE r.comandoTv = :comandoTv"),
    @NamedQuery(name = "RemotoContatos.findByUsuarioConectado", query = "SELECT r FROM RemotoContatos r WHERE r.usuarioConectado = :usuarioConectado"),
    @NamedQuery(name = "RemotoContatos.findByTipo", query = "SELECT r FROM RemotoContatos r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RemotoContatos.findByStatus", query = "SELECT r FROM RemotoContatos r WHERE r.status = :status"),
    @NamedQuery(name = "RemotoContatos.findByComentario", query = "SELECT r FROM RemotoContatos r WHERE r.comentario = :comentario"),
    @NamedQuery(name = "RemotoContatos.findBySituacao", query = "SELECT r FROM RemotoContatos r WHERE r.situacao = :situacao"),
    @NamedQuery(name = "RemotoContatos.findByNomeContato", query = "SELECT r FROM RemotoContatos r WHERE r.nomeContato = :nomeContato"),
    @NamedQuery(name = "RemotoContatos.findByServidor", query = "SELECT r FROM RemotoContatos r WHERE r.servidor = :servidor")})
public class RemotoContatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_contato")
    private Integer iContato;
    @Size(max = 2147483647)
    @Column(name = "ip")
    private String ip;
    @Column(name = "porta")
    private Integer porta;
    @Size(max = 2147483647)
    @Column(name = "dominio")
    private String dominio;
    @Size(max = 2147483647)
    @Column(name = "usuario_windows")
    private String usuarioWindows;
    @Size(max = 2147483647)
    @Column(name = "senha_windows")
    private String senhaWindows;
    @Size(max = 2147483647)
    @Column(name = "vpn")
    private String vpn;
    @Size(max = 2147483647)
    @Column(name = "id_tv")
    private String idTv;
    @Size(max = 2147483647)
    @Column(name = "senha_tv")
    private String senhaTv;
    @Size(max = 2147483647)
    @Column(name = "comando_rpd")
    private String comandoRpd;
    @Size(max = 2147483647)
    @Column(name = "comando_tv")
    private String comandoTv;
    @Size(max = 2147483647)
    @Column(name = "usuario_conectado")
    private String usuarioConectado;
    @Size(max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @Size(max = 2147483647)
    @Column(name = "nome_contato")
    private String nomeContato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "servidor")
    private boolean servidor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iContato")
    private List<RemotoListaEspera> remotoListaEsperaList;
    @JoinColumn(name = "i_entidade", referencedColumnName = "i_entidade")
    @ManyToOne
    private CaspEntidades iEntidade;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne
    private CaspUsuarios iUsuario;

    public RemotoContatos() {
    }

    public RemotoContatos(Integer iContato) {
        this.iContato = iContato;
    }

    public RemotoContatos(Integer iContato, boolean servidor) {
        this.iContato = iContato;
        this.servidor = servidor;
    }

    public Integer getIContato() {
        return iContato;
    }

    public void setIContato(Integer iContato) {
        this.iContato = iContato;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getUsuarioWindows() {
        return usuarioWindows;
    }

    public void setUsuarioWindows(String usuarioWindows) {
        this.usuarioWindows = usuarioWindows;
    }

    public String getSenhaWindows() {
        return senhaWindows;
    }

    public void setSenhaWindows(String senhaWindows) {
        this.senhaWindows = senhaWindows;
    }

    public String getVpn() {
        return vpn;
    }

    public void setVpn(String vpn) {
        this.vpn = vpn;
    }

    public String getIdTv() {
        return idTv;
    }

    public void setIdTv(String idTv) {
        this.idTv = idTv;
    }

    public String getSenhaTv() {
        return senhaTv;
    }

    public void setSenhaTv(String senhaTv) {
        this.senhaTv = senhaTv;
    }

    public String getComandoRpd() {
        return comandoRpd;
    }

    public void setComandoRpd(String comandoRpd) {
        this.comandoRpd = comandoRpd;
    }

    public String getComandoTv() {
        return comandoTv;
    }

    public void setComandoTv(String comandoTv) {
        this.comandoTv = comandoTv;
    }

    public String getUsuarioConectado() {
        return usuarioConectado;
    }

    public void setUsuarioConectado(String usuarioConectado) {
        this.usuarioConectado = usuarioConectado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public boolean getServidor() {
        return servidor;
    }

    public void setServidor(boolean servidor) {
        this.servidor = servidor;
    }

    @XmlTransient
    public List<RemotoListaEspera> getRemotoListaEsperaList() {
        return remotoListaEsperaList;
    }

    public void setRemotoListaEsperaList(List<RemotoListaEspera> remotoListaEsperaList) {
        this.remotoListaEsperaList = remotoListaEsperaList;
    }

    public CaspEntidades getIEntidade() {
        return iEntidade;
    }

    public void setIEntidade(CaspEntidades iEntidade) {
        this.iEntidade = iEntidade;
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
        hash += (iContato != null ? iContato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemotoContatos)) {
            return false;
        }
        RemotoContatos other = (RemotoContatos) object;
        if ((this.iContato == null && other.iContato != null) || (this.iContato != null && !this.iContato.equals(other.iContato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.RemotoContatos[ iContato=" + iContato + " ]";
    }
    
}
