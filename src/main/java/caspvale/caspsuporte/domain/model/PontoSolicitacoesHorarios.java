/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "ponto_solicitacoes_horarios",schema = "casp1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoSolicitacoesHorarios.findAll", query = "SELECT p FROM PontoSolicitacoesHorarios p"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByISolicitacao", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.iSolicitacao = :iSolicitacao"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByDatasolicitacao", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.datasolicitacao = :datasolicitacao"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByMotivoAlteracao", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.motivoAlteracao = :motivoAlteracao"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByAutorizado", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.autorizado = :autorizado"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByTipoRegistro", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.tipoRegistro = :tipoRegistro"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByHoraRegistrada", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.horaRegistrada = :horaRegistrada"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByHoraAlterada", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.horaAlterada = :horaAlterada"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findByParecer", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.parecer = :parecer"),
    @NamedQuery(name = "PontoSolicitacoesHorarios.findBySituacao", query = "SELECT p FROM PontoSolicitacoesHorarios p WHERE p.situacao = :situacao")})
public class PontoSolicitacoesHorarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_solicitacao")
    private Integer iSolicitacao;
    @Column(name = "datasolicitacao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datasolicitacao;
    @Size(max = 2147483647)
    @Column(name = "motivo_alteracao")
    private String motivoAlteracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autorizado")
    private boolean autorizado;
    @Size(max = 2147483647)
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    @Column(name = "hora_registrada")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaRegistrada;
    @Column(name = "hora_alterada")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime horaAlterada;
    @Size(max = 2147483647)
    @Column(name = "parecer")
    private String parecer;
    @Size(max = 2147483647)
    @Column(name = "situacao")
    private String situacao;
    @JoinColumn(name = "i_horario", referencedColumnName = "i_horario")
    @ManyToOne(optional = false)
    private PontoHorarios iHorario;

    public PontoSolicitacoesHorarios() {
    }

    public PontoSolicitacoesHorarios(Integer iSolicitacao) {
        this.iSolicitacao = iSolicitacao;
    }

    public PontoSolicitacoesHorarios(Integer iSolicitacao, boolean autorizado) {
        this.iSolicitacao = iSolicitacao;
        this.autorizado = autorizado;
    }

    public Integer getISolicitacao() {
        return iSolicitacao;
    }

    public void setISolicitacao(Integer iSolicitacao) {
        this.iSolicitacao = iSolicitacao;
    }

    public LocalDateTime getDatasolicitacao() {
        return datasolicitacao;
    }

    public void setDatasolicitacao(LocalDateTime datasolicitacao) {
        this.datasolicitacao = datasolicitacao;
    }

    public String getMotivoAlteracao() {
        return motivoAlteracao;
    }

    public void setMotivoAlteracao(String motivoAlteracao) {
        this.motivoAlteracao = motivoAlteracao;
    }

    public boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public LocalDateTime getHoraRegistrada() {
        return horaRegistrada;
    }

    public void setHoraRegistrada(LocalDateTime horaRegistrada) {
        this.horaRegistrada = horaRegistrada;
    }

    public LocalDateTime getHoraAlterada() {
        return horaAlterada;
    }

    public void setHoraAlterada(LocalDateTime horaAlterada) {
        this.horaAlterada = horaAlterada;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public PontoHorarios getIHorario() {
        return iHorario;
    }

    public void setIHorario(PontoHorarios iHorario) {
        this.iHorario = iHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iSolicitacao != null ? iSolicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PontoSolicitacoesHorarios)) {
            return false;
        }
        PontoSolicitacoesHorarios other = (PontoSolicitacoesHorarios) object;
        if ((this.iSolicitacao == null && other.iSolicitacao != null) || (this.iSolicitacao != null && !this.iSolicitacao.equals(other.iSolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "caspvale.caspsuporte.domain.model.PontoSolicitacoesHorarios[ iSolicitacao=" + iSolicitacao + " ]";
    }
    
}
