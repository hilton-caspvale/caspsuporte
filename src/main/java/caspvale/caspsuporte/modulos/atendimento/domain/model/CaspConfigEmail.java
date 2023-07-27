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
@Table(name = "casp_config_email",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaspConfigEmail.findAll", query = "SELECT c FROM CaspConfigEmail c"),
    @NamedQuery(name = "CaspConfigEmail.findByIConfig", query = "SELECT c FROM CaspConfigEmail c WHERE c.iConfig = :iConfig"),
    @NamedQuery(name = "CaspConfigEmail.findByEmail", query = "SELECT c FROM CaspConfigEmail c WHERE c.email = :email"),
    @NamedQuery(name = "CaspConfigEmail.findBySenha", query = "SELECT c FROM CaspConfigEmail c WHERE c.senha = :senha"),
    @NamedQuery(name = "CaspConfigEmail.findByServidorSmtp", query = "SELECT c FROM CaspConfigEmail c WHERE c.servidorSmtp = :servidorSmtp"),
    @NamedQuery(name = "CaspConfigEmail.findByPorta", query = "SELECT c FROM CaspConfigEmail c WHERE c.porta = :porta"),
    @NamedQuery(name = "CaspConfigEmail.findByAutenticacaoSsl", query = "SELECT c FROM CaspConfigEmail c WHERE c.autenticacaoSsl = :autenticacaoSsl"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailAbrir", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailAbrir = :emailAbrir"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailAtender", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailAtender = :emailAtender"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailAnexo", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailAnexo = :emailAnexo"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailEncaminhar", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailEncaminhar = :emailEncaminhar"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailEncerrar", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailEncerrar = :emailEncerrar"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemAbertura", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemAbertura = :mensagemAbertura"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemAtendimento", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemAtendimento = :mensagemAtendimento"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemAssentamento", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemAssentamento = :mensagemAssentamento"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemEncerramento", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemEncerramento = :mensagemEncerramento"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemCancelamento", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemCancelamento = :mensagemCancelamento"),
    @NamedQuery(name = "CaspConfigEmail.findByMensagemEncaminhamento", query = "SELECT c FROM CaspConfigEmail c WHERE c.mensagemEncaminhamento = :mensagemEncaminhamento"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailCancelar", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailCancelar = :emailCancelar"),
    @NamedQuery(name = "CaspConfigEmail.findByEmailFrom", query = "SELECT c FROM CaspConfigEmail c WHERE c.emailFrom = :emailFrom")})
public class CaspConfigEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_config")
    private Integer iConfig;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "senha")
    private String senha;
    @Size(max = 2147483647)
    @Column(name = "servidor_smtp")
    private String servidorSmtp;
    @Column(name = "porta")
    private Integer porta;
    @Column(name = "autenticacao_ssl")
    private Boolean autenticacaoSsl;
    @Column(name = "email_abrir")
    private Boolean emailAbrir;
    @Column(name = "email_atender")
    private Boolean emailAtender;
    @Column(name = "email_anexo")
    private Boolean emailAnexo;
    @Column(name = "email_encaminhar")
    private Boolean emailEncaminhar;
    @Column(name = "email_encerrar")
    private Boolean emailEncerrar;
    @Size(max = 2147483647)
    @Column(name = "mensagem_abertura")
    private String mensagemAbertura;
    @Size(max = 2147483647)
    @Column(name = "mensagem_atendimento")
    private String mensagemAtendimento;
    @Size(max = 2147483647)
    @Column(name = "mensagem_assentamento")
    private String mensagemAssentamento;
    @Size(max = 2147483647)
    @Column(name = "mensagem_encerramento")
    private String mensagemEncerramento;
    @Size(max = 2147483647)
    @Column(name = "mensagem_cancelamento")
    private String mensagemCancelamento;
    @Size(max = 2147483647)
    @Column(name = "mensagem_encaminhamento")
    private String mensagemEncaminhamento;
    @Column(name = "email_cancelar")
    private Boolean emailCancelar;
    @Size(max = 2147483647)
    @Column(name = "email_from")
    private String emailFrom;

    public CaspConfigEmail() {
    }

    public CaspConfigEmail(Integer iConfig) {
        this.iConfig = iConfig;
    }

    public Integer getIConfig() {
        return iConfig;
    }

    public void setIConfig(Integer iConfig) {
        this.iConfig = iConfig;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidorSmtp() {
        return servidorSmtp;
    }

    public void setServidorSmtp(String servidorSmtp) {
        this.servidorSmtp = servidorSmtp;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public Boolean getAutenticacaoSsl() {
        return autenticacaoSsl;
    }

    public void setAutenticacaoSsl(Boolean autenticacaoSsl) {
        this.autenticacaoSsl = autenticacaoSsl;
    }

    public Boolean getEmailAbrir() {
        return emailAbrir;
    }

    public void setEmailAbrir(Boolean emailAbrir) {
        this.emailAbrir = emailAbrir;
    }

    public Boolean getEmailAtender() {
        return emailAtender;
    }

    public void setEmailAtender(Boolean emailAtender) {
        this.emailAtender = emailAtender;
    }

    public Boolean getEmailAnexo() {
        return emailAnexo;
    }

    public void setEmailAnexo(Boolean emailAnexo) {
        this.emailAnexo = emailAnexo;
    }

    public Boolean getEmailEncaminhar() {
        return emailEncaminhar;
    }

    public void setEmailEncaminhar(Boolean emailEncaminhar) {
        this.emailEncaminhar = emailEncaminhar;
    }

    public Boolean getEmailEncerrar() {
        return emailEncerrar;
    }

    public void setEmailEncerrar(Boolean emailEncerrar) {
        this.emailEncerrar = emailEncerrar;
    }

    public String getMensagemAbertura() {
        return mensagemAbertura;
    }

    public void setMensagemAbertura(String mensagemAbertura) {
        this.mensagemAbertura = mensagemAbertura;
    }

    public String getMensagemAtendimento() {
        return mensagemAtendimento;
    }

    public void setMensagemAtendimento(String mensagemAtendimento) {
        this.mensagemAtendimento = mensagemAtendimento;
    }

    public String getMensagemAssentamento() {
        return mensagemAssentamento;
    }

    public void setMensagemAssentamento(String mensagemAssentamento) {
        this.mensagemAssentamento = mensagemAssentamento;
    }

    public String getMensagemEncerramento() {
        return mensagemEncerramento;
    }

    public void setMensagemEncerramento(String mensagemEncerramento) {
        this.mensagemEncerramento = mensagemEncerramento;
    }

    public String getMensagemCancelamento() {
        return mensagemCancelamento;
    }

    public void setMensagemCancelamento(String mensagemCancelamento) {
        this.mensagemCancelamento = mensagemCancelamento;
    }

    public String getMensagemEncaminhamento() {
        return mensagemEncaminhamento;
    }

    public void setMensagemEncaminhamento(String mensagemEncaminhamento) {
        this.mensagemEncaminhamento = mensagemEncaminhamento;
    }

    public Boolean getEmailCancelar() {
        return emailCancelar;
    }

    public void setEmailCancelar(Boolean emailCancelar) {
        this.emailCancelar = emailCancelar;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iConfig != null ? iConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaspConfigEmail)) {
            return false;
        }
        CaspConfigEmail other = (CaspConfigEmail) object;
        if ((this.iConfig == null && other.iConfig != null) || (this.iConfig != null && !this.iConfig.equals(other.iConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.CaspConfigEmail[ iConfig=" + iConfig + " ]";
    }
    
}
